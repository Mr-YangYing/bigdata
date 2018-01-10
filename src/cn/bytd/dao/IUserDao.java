package cn.bytd.dao;

import cn.bytd.domain.User;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

/**
 * 
 * 说明:用户Dao
 * @author yangying
 * @version 1.0
 * @date 2017年12月26日 上午11:19:28
 *
 *
 */
public interface IUserDao {
	/**
	 * 通过用户名查询用户
	 * @param username
	 * @return
	 */
	User findUserByUserName(String username);

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
	User getById(String id);

	/**
	 * 修改
	 * 
	 * @param teacher
	 */
	void update(User user);

	/**
	 * 添加
	 * 
	 * @param student
	 */
	void insert(User user);
	/**
	 * 添加用户角色
	 * @param userId
	 * @param roleId
	 */
	void insertUserRole(String userId, String roleId);
	/**
	 * 删除用户角色
	 * @param userId
	 */
	void deleteUserRole(String userId);
	/**
	 * 修改密码
	 * @param password
	 * @param id
	 */
	void editPassword(String password, String id);
}
