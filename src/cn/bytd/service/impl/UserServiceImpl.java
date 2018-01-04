package cn.bytd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.IRoleDao;
import cn.bytd.dao.IUserDao;
import cn.bytd.domain.Role;
import cn.bytd.domain.Student;
import cn.bytd.domain.User;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.IRoleService;
import cn.bytd.service.IUserService;
import cn.bytd.util.StringUtils;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;

	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return userDao.query(qo);
	}

	/**
	 * 根据ID删除
	 */
	public void delete(String id) {
		userDao.delete(id);
	}

	public void batchDelete(String[] ids) {
		userDao.batchDelete(ids);
	}

	public User getById(String id) {
		return userDao.getById(id);
	}

	/**
	 * 修改
	 */
	public void update(User user) {
		userDao.update(user);
	}

	/**
	 * 添加
	 */
	public void insert(User user) {
		userDao.insert(user);
	}

	public void insert(String userId, String[] roleIds) {
		for (String roleId : roleIds) {
			userDao.insertUserRole(userId, roleId);
		}
	}

	@Override
	public void update(String userId, String[] roleIds) {
		userDao.deleteUserRole(userId);
		for (String roleId : roleIds) {
			userDao.insertUserRole(userId, roleId);
		}
	};
}
