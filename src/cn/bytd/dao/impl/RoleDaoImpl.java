package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import cn.bytd.dao.IPermissionDao;
import cn.bytd.dao.IRoleDao;
import cn.bytd.dao.IUserDao;
import cn.bytd.domain.Permission;
import cn.bytd.domain.Role;
import cn.bytd.domain.Student;
import cn.bytd.domain.User;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;
@Repository(value="roleDao")
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	private IPermissionDao permissionDao;
	
	private RowMapper<Role> rm = new RowMapperRole();//Role通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "role", rm,jdbcTemplate);
	}


	@Override
	public Set getRoleByUserId(String id) {
		List<Role> list = jdbcTemplate.query("select * from role where id in (select role_id from user_role where user_id = ?)",rm, id);
		Set<Role> roles = new HashSet<>(list);
		return roles;
	}

	
	/**
	 * 根据id删除
	 */
	public void delete(String id) {
		
		jdbcTemplate.update("delete from role_permission where role_id = ?", id);
		jdbcTemplate.update("delete from user_role where role_id = ?", id);
		jdbcTemplate.update("delete from role where id = ?", id);
	}



	
	/**
	 * 批量删除
	 */
	public void batchDelete(String[] ids) {
		final List<String> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		//删除关联表的角色
		jdbcTemplate.batchUpdate("delete from role_permission where role_id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
		//删除关联表的角色
		jdbcTemplate.batchUpdate("delete from user_role where role_id = ?",new BatchPreparedStatementSetter() {
			
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
		jdbcTemplate.batchUpdate("delete from role where id = ?",new BatchPreparedStatementSetter() {
			
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
	public Role getById(String id) {
		Role role = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			role = jdbcTemplate.queryForObject("select * from role where id = ?",rm,id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return role;
	}
	

	@Override
	public Role getRoleByCode(String code) {
		Role role = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			role = jdbcTemplate.queryForObject("select * from role where code = ?",rm,code);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return role;
	}



	/**
	 * 修改
	 */
	public void update(Role role) {
		jdbcTemplate.update("update role set name = ?,code = ?,description = ? where id = ?",
				role.getName(),role.getCode(),role.getDescription(),role.getId());
	}
	
	@Override
	public void updateRolePermission(String roleId) {
		jdbcTemplate.update("delete from role_permission where role_id = ?",roleId);
	}



	/**
	 * 添加
	 */
	public void insert(Role role) {

		jdbcTemplate.update("insert into role(id,name,code,description)values(?,?,?,?)", 
				role.getId(),role.getName(),role.getCode(),role.getDescription());
	}
	/**
	 * 添加角色的权限
	 */
	public void insertRolePermission(String roleId,String permissionId) {
		jdbcTemplate.update("insert into role_permission(role_id,permission_id)values(?,?)", 
				roleId,permissionId);
	}
	

	public List<Role> roleList() {
		return jdbcTemplate.query("select * from role", rm);
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
			List<Permission> PermissionList = permissionDao.getPermissionByRoleId(rs.getString("id"));
			Set<Permission> PermissionSet = new HashSet<>();
			for (Permission permission : PermissionList) {
				PermissionSet.add(permission);
			}
			role.setPermissions(PermissionSet);
			return role;
		}

	}



}
