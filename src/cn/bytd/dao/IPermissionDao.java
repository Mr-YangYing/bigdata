package cn.bytd.dao;


import java.util.List;

import cn.bytd.domain.Permission;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

/**
 * 
 * 说明:权限Dao
 * @author yangying
 * @version 1.0
 * @date 2017年12月26日 下午7:30:53
 *
 *
 */
public interface IPermissionDao {

	/**
	 * 高级查询
	 * @param qo
	 * @return
	 */
	PageResult query(IQueryObject qo);
	
	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	void delete(String id);


	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void batchDelete(String[] ids);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 */
	Permission getById(String id);

	/**
	 * 修改
	 * 
	 * @param role
	 */
	void update(Permission permission);

	/**
	 * 添加
	 * 
	 * @param role
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
	/**
	 * 根据角色Id获取权限
	 * @param id
	 * @return
	 */
	List<Permission> getPermissionByRoleId(String id);

}
