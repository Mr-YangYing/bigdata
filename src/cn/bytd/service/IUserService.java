package cn.bytd.service;

import cn.bytd.domain.User;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

public interface IUserService {

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
	 * @param roleIds
	 */
	void insert(String userId, String[] roleIds);
	/**
	 * 更新用户的角色
	 * @param userId
	 * @param roleIds
	 */
	void update(String userId, String[] roleIds);
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 */
	void editPassword(String id, String password);
}
