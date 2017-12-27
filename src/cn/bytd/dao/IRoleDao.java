package cn.bytd.dao;

import java.util.Set;

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

}
