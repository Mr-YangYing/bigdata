package cn.bytd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IRoleDao;
import cn.bytd.dao.IUserDao;
import cn.bytd.domain.Student;
import cn.bytd.domain.User;
@Repository(value="userDao")
public class UserDaoImpl implements IUserDao {

	private RowMapper<User> rm = new RowMapperUser();//User通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Resource(name="roleDao")
	private IRoleDao roleDao;
	
	@Override
	public User findUserByUserName(String username) {
		User user = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			user = jdbcTemplate.queryForObject("select * from user where username = ?",rm,username);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	
	/**
	 * 
	 * 内部类:User类结果集处理器
	 *
	 */
	private class RowMapperUser implements RowMapper<User> {
		
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setGender(rs.getString("gender"));
			user.setStation(rs.getString("station"));
			user.setTelephone(rs.getString("telephone"));
			user.setRemark(rs.getString("remark"));
			user.setRoles(roleDao.getRoleByUserId(rs.getString("id")));
			return user;
		}

	}
}
