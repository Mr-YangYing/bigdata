package cn.bytd.service;

import java.util.List;

import cn.bytd.domain.Role;
import cn.bytd.domain.Student;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

public interface IRoleService {
	/**
	 * 高级查询
	 * @param qo
	 * @return
	 */
	PageResult query(IQueryObject qo);
	
	/**
	 * 根据id删除
	 */
	void delete(String id);
	
	
	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void batchDelete(String[] ids);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 */
	Role getById(String id);

	/**
	 * 修改
	 * 
	 * @param role
	 */
	void update(Role role);

	/**
	 * 添加
	 * 
	 * @param role
	 */
	void insert(Role role);
	/**
	 * 添加角色的权限
	 * @param role
	 * @param permissionIds
	 */
	void insert(String roleId,String permissionIds);
	/**
	 * 获取所有权限
	 * @return
	 */
	List<Role> roleList();
	/**
	 * 更新角色的权限
	 * @param roleId
	 * @param permissionIds
	 */
	void update(String roleId, String permissionIds);
}
