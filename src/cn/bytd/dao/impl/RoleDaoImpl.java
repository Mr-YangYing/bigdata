package cn.bytd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import cn.bytd.domain.Role;
import cn.bytd.domain.Student;
import cn.bytd.domain.User;
@Repository(value="roleDao")
public class RoleDaoImpl implements IRoleDao {

	private RowMapper<Role> rm = new RowMapperRole();//Role通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	


	@Override
	public Set getRoleByUserId(String id) {
		List<Role> list = jdbcTemplate.query("select * from role where id in (select role_id from user_role where user_id = ?)",rm, id);
		Set<Role> roles = new HashSet<>(list);
		return roles;
	}

	
	/**
	 * 
	 * 内部类:Role类结果集处理器
	 *
	 */
	private class RowMapperRole implements RowMapper<Role> {
		
		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			role.setId(rs.getString("id"));
			role.setName(rs.getString("name"));
			role.setCode(rs.getString("code"));
			role.setDescription(rs.getString("description"));
			return role;
		}

	}

}
