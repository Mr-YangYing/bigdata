package cn.bytd.dao;

import java.util.List;
import java.util.Set;

import cn.bytd.domain.Role;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

/**
 * 
 * 说明:角色Dao
 * @author yangying
 * @version 1.0
 * @date 2017年12月26日 下午7:30:53
 *
 *
 */
public interface IRoleDao {
	/**
	 * 根据用户Id获取角色
	 * @param id
	 * @return
	 */
	Set getRoleByUserId(String id);
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
	 * @param roleId
	 * @param permissionId
	 */
	void insertRolePermission(String roleId,String permissionId);
	/**
	 * 获取所有角色
	 * @return
	 */
	List<Role> roleList();
	/**
	 * 更新角色的权限
	 * @param roleId
	 * 
	 */
	void updateRolePermission(String roleId);
	/**
	 * 根据角色关键字查询角色
	 * @param string
	 * @return
	 */
	Role getRoleByCode(String code);
}
