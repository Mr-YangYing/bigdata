package cn.bytd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.IRoleDao;
import cn.bytd.domain.Role;
import cn.bytd.domain.Student;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.IRoleService;
import cn.bytd.util.StringUtils;
@Service(value="roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return roleDao.query(qo);
	}
	
	/**
	 * 根据ID删除
	 */
	public void delete(String id) {
		roleDao.delete(id);
	}

	public void batchDelete(String[] ids){
		roleDao.batchDelete(ids);
	}

	public Role getById(String id) {
		return roleDao.getById(id);
	}
	/**
	 * 修改
	 */
	public void update(Role role) {
		roleDao.update(role);
	}
	
	public void update(String roleId, String permissionIds){
		roleDao.updateRolePermission(roleId);
		if(StringUtils.hasLength(permissionIds)){
			String[] ids = permissionIds.split(",");
			for (String permissionId : ids) {
				roleDao.insertRolePermission(roleId, permissionId);
			}
		}
	}
	
	/**
	 * 添加
	 */
	public void insert(Role role) {
		roleDao.insert(role);
	}
	/**
	 * 添加角色的权限
	 */
	public void insert(String roleId,String permissionIds) {
		if(StringUtils.hasLength(permissionIds)){
			String[] ids = permissionIds.split(",");
			for (String permissionId : ids) {
				roleDao.insertRolePermission(roleId, permissionId);
			}
		}
	}

	public List<Role> roleList(){
		return roleDao.roleList();
	}
}
