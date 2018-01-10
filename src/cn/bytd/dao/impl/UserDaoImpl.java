package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IRoleDao;
import cn.bytd.dao.IUserDao;
import cn.bytd.domain.Role;
import cn.bytd.domain.Student;
import cn.bytd.domain.User;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;
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
	
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "user", rm,jdbcTemplate);
	}

	/**
	 * 根据id删除
	 */
	public void delete(String id) {
		
		jdbcTemplate.update("delete from user_role where user_id = ?", id);
		jdbcTemplate.update("delete from user where id = ?", id);
	}


	@Override
	public void deleteUserRole(String userId) {
		jdbcTemplate.update("delete from user_role where user_id = ?",userId);
	}

	
	/**
	 * 批量删除
	 */
	public void batchDelete(String[] ids) {
		final List<String> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		//删除关联表的用户
		jdbcTemplate.batchUpdate("delete from user_role where user_id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
		//删除
		jdbcTemplate.batchUpdate("delete from user where id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
	}


	/**
	 * 根据id获取
	 */
	public User getById(String id) {
		User user = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			user = jdbcTemplate.queryForObject("select * from user where id = ?",rm,id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}
	


	/**
	 * 修改
	 */
	public void update(User user) {
		jdbcTemplate.update("update user set username = ?,password = ?,gender = ?,station = ?,"
				+ "telephone = ?,remark = ? where id = ?",
				user.getUsername(),user.getPassword(),user.getGender(),user.getStation(),
				user.getTelephone(),user.getRemark(),user.getId());
	}


	/**
	 * 添加
	 */
	public void insert(User user) {
		jdbcTemplate.update("insert into user(id,username,password,gender,station,telephone,remark)values(?,?,?,?,?,?,?)", 
				user.getId(),user.getUsername(),user.getPassword(),user.getGender(),user.getStation(),
				user.getTelephone(),user.getRemark());
	}
	
	public void insertUserRole(String userId, String roleId){
		jdbcTemplate.update("insert into user_role(user_id,role_id)values(?,?)",
				userId,roleId);
	};
	

	@Override
	public void editPassword(String password, String id) {
		jdbcTemplate.update("UPDATE User SET password = ? WHERE id = ?",password,id);
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
