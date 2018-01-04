package cn.bytd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.IPermissionDao;
import cn.bytd.domain.Permission;
import cn.bytd.domain.Role;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.IPermissionService;
@Service(value="permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	private IPermissionDao permissionDao;
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return permissionDao.query(qo);
	}
	
	/**
	 * 根据ID删除
	 */
	public void delete(String id) {
		permissionDao.delete(id);
	}
	
	public List<Permission> getPermissionByRoleId(String id){
		return permissionDao.getPermissionByRoleId(id);
	}

	public void batchDelete(String[] ids){
		permissionDao.batchDelete(ids);
	}

	public Permission getById(String id) {
		return permissionDao.getById(id);
	}
	/**
	 * 修改
	 */
	public void update(Permission permission) {
		permissionDao.update(permission);
	}
	/**
	 * 添加
	 */
	public void insert(Permission permission) {
		permissionDao.insert(permission);
	}

	public List<Permission> getPermissionList() {
		return permissionDao.getPermissionList();
	}

	public List<Permission> getPermissionListZtree() {
		return permissionDao.getPermissionListZtree();
	}
}
