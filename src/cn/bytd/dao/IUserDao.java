package cn.bytd.dao;

import cn.bytd.domain.User;

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

}
