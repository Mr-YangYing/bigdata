package cn.bytd.service;

import java.util.List;

import cn.bytd.domain.Permission;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

public interface IPermissionService {

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
	Permission getById(String id);
	/**
	 * 根据角色id获取权限
	 * 
	 * @param id
	 */
	List<Permission> getPermissionByRoleId(String id);

	/**
	 * 修改
	 * 
	 * @param teacher
	 */
	void update(Permission permission);

	/**
	 * 添加
	 * 
	 * @param student
	 */
	void insert(Permission permission);
	/**
	 * 获取所有权限
	 * @return
	 */
	List<Permission> getPermissionList();
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Permission> getPermissionListZtree();
}
