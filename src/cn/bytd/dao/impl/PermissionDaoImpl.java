package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IPermissionDao;
import cn.bytd.domain.Permission;
import cn.bytd.domain.Role;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;
@Repository(value="permissionDao")
public class PermissionDaoImpl implements IPermissionDao{

	private RowMapper<Permission> rm = new RowMapperPermission();//Permission通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "permission", rm,jdbcTemplate);
	}


	
	/**
	 * 根据id删除
	 */
	public void delete(String id) {
		
		jdbcTemplate.update("delete from role_permission where permission_id = ?", id);
		jdbcTemplate.update("delete from permission where pid = ?", id);
		jdbcTemplate.update("delete from permission where id = ?", id);
	}

	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Permission> getPermissionList(){
		List<Permission> permissionList = jdbcTemplate.query("select * from permission", rm);
		return permissionList;
	};
	
	public List<Permission> getPermissionByRoleId(String id){
		return jdbcTemplate.query("select * from permission where id in "
				+ "(select permission_id from role_permission where role_id = ?)", rm,id);
	}
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Permission> getPermissionListZtree(){
		List<Permission> permissionList = jdbcTemplate.query("select * from permission",new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				Permission permission = new Permission();
				permission.setId(rs.getString("id"));
				permission.setName(rs.getString("name"));
				permission.setCode(rs.getString("code"));
				permission.setDescription(rs.getString("description"));
				permission.setPage(rs.getString("page"));
				permission.setGeneratemenu(rs.getString("generatemenu"));
				permission.setZindex(rs.getInt("zindex"));
				//permission.setpId(rs.getString("pid"));
				return permission;
			}
		});
		return permissionList;
	};

	
	/**
	 * 批量删除
	 */
	public void batchDelete(String[] ids) {
		final List<String> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		//删除关联表的角色
		jdbcTemplate.batchUpdate("delete from role_permission where permission_id = ?",new BatchPreparedStatementSetter() {
			
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
		jdbcTemplate.batchUpdate("delete from permission where id = ?",new BatchPreparedStatementSetter() {
			
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
	public Permission getById(String id) {
		Permission permission = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			permission = jdbcTemplate.queryForObject("select * from permission where id = ?",rm,id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return permission;
	}
	


	/**
	 * 修改
	 */
	public void update(Permission permission) {
		jdbcTemplate.update("update permission set name = ?,code = ?,description = ?,page = ?,"
				+ "generatemenu = ?,zindex = ? where id = ?",
				permission.getName(),permission.getCode(),permission.getDescription(),
				permission.getPage(),permission.getGeneratemenu(),permission.getZindex(),permission.getId());
	}


	/**
	 * 添加
	 */
	public void insert(Permission permission) {
		System.out.println(permission);
		jdbcTemplate.update("insert into permission(id,name,code,description,page,generatemenu,zindex,pid)values(?,?,?,?,?,?,?,?)", 
				permission.getId(),permission.getName(),permission.getCode(),permission.getDescription(),
				permission.getPage(),permission.getGeneratemenu(),permission.getZindex(),
				permission.getParentPermission() == null ? null : permission.getParentPermission().getId());
	}
	
	
	/**
	 * 
	 * 内部类:Permission类结果集处理器
	 *
	 */
	private class RowMapperPermission implements RowMapper<Permission> {
		
		@Override
		public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
			Permission permission = new Permission();
			permission.setId(rs.getString("id"));
			permission.setName(rs.getString("name"));
			permission.setCode(rs.getString("code"));
			permission.setDescription(rs.getString("description"));
			permission.setPage(rs.getString("page"));
			permission.setGeneratemenu(rs.getString("generatemenu"));
			permission.setZindex(rs.getInt("zindex"));
			permission.setParentPermission(getById(rs.getString("pid")));
			return permission;
		}

	}
}
