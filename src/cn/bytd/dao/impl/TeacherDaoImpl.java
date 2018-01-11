package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IRoleDao;
import cn.bytd.dao.ITeacherDao;
import cn.bytd.dao.IUserDao;
import cn.bytd.domain.Course;
import cn.bytd.domain.Role;
import cn.bytd.domain.Task;
import cn.bytd.domain.Teacher;
import cn.bytd.domain.User;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;
import cn.bytd.util.MD5Utils;

/**
 * 
 * 说明:教师Dao
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:22:04
 *
 */
@Repository("teacherDao")
public class TeacherDaoImpl implements ITeacherDao {

	private RowMapper<Teacher> rm = new RowMapperTeacher();//Teacher通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;

	/**
	 * 查询所有
	 */
	public List<Teacher> list() {
		return jdbcTemplate.query("select * from teacher", rm, new Object[] {});
	}

	/**
	 * 高级查询
	 * @return
	 */
	/*public List<Teacher> query(TeacherQueryObject qo) {
		String sql = "select * from teacher" + qo.getQuery();
		return jdbcTemplate.query(sql, rm, qo.getParameters().toArray());
	}*/
	

	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "teacher", rm,jdbcTemplate);
	}


	/**
	 * 分页查询
	 */
	@SuppressWarnings("deprecation")
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		//查询结果集,得到listData
		String baseSql = "select * from teacher limit ?,?";
		List<Teacher> listData = jdbcTemplate.query(baseSql, rm, (currentPage - 1) * pageSize, pageSize);
		//查询结果总数,得到totalCount
		String countSql = "select count(*) from teacher";
		int count = jdbcTemplate.queryForObject(countSql, Integer.class);
		return new PageResult(listData, Integer.valueOf(count), currentPage, pageSize);
	}

	public Teacher getByTeacherAccount(String teacherAccount){
		Teacher teacher = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			teacher = jdbcTemplate.queryForObject("select * from teacher where teacherAccount = ?",rm,teacherAccount);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return teacher;
	}

	/**
	 * 根据id删除
	 */
	public void delete(String id) {
		//删除关联表的教师
		jdbcTemplate.update("delete from course_teacher_config where tea_id = ?", id);
		jdbcTemplate.update("delete from teacher where id = ?", id);
		//删除教师用户
		userDao.delete(id);
		
	}

	/**
	 * 批量删除
	 */
	public void batchDelete(String[] ids) {
		final List<String> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
			userDao.delete(ids[i]);
		}
		//删除关联表的教师
		jdbcTemplate.batchUpdate("delete from course_teacher_config where tea_id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
		//删除教师
		jdbcTemplate.batchUpdate("delete from teacher where id = ?",new BatchPreparedStatementSetter() {
			
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
	public Teacher getById(String id) {
		Teacher teacher = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			teacher = jdbcTemplate.queryForObject("select * from teacher where id = ?",rm,id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return teacher;
	}
	

	/**
	 * 修改
	 */
	public void update(Teacher teacher) {
		//修改教师
		jdbcTemplate.update("update teacher set teacherAccount = ?,teacherName = ?,positionalTitles = ? where id = ?",
				teacher.getTeacherAccount(),teacher.getTeacherName(),teacher.getPositionalTitles(),teacher.getId());
		//修改教师用户
		User user = new User();
		user.setId(teacher.getId());
		user.setUsername(teacher.getTeacherAccount());
		userDao.updateTeacherUser(user);
	}
	
	/**
	 * 添加
	 */
	public void insert(Teacher teacher){
		String userId = UUID.randomUUID().toString();
		jdbcTemplate.update("insert into teacher(id,teacherAccount,teacherName,positionalTitles)values(?,?,?,?)"
				,userId,teacher.getTeacherAccount(),teacher.getTeacherName(),teacher.getPositionalTitles());
		//添加用户
		jdbcTemplate.update("insert into user(id,username,password)values(?,?,?)",userId,
				teacher.getTeacherAccount(),MD5Utils.md5("123456"));
		//添加用户角色为教师
		Role role = roleDao.getRoleByCode("teacher");
		jdbcTemplate.update("insert into user_role(user_id,role_id)values(?,?)",userId,role.getId());
	}

	
	
	/**
	 * 获取列名
	 */
	public List<String> getColumnName() {
		List<String> list = new ArrayList<>();
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from teacher");
		SqlRowSetMetaData metaData = sqlRowSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 2; i <=columnCount; i++) {
			list.add(metaData.getColumnName(i));
		}
		return list;
	}



	/**
	 * 批量增加
	 */
	public void batchUpdate(List<Teacher> list) {
		
		final List<Teacher> tempList = list;
		List<String> uuidList = new ArrayList<>();//存放id
		////////////添加教师用户
		for (Teacher teacher : tempList) {
			String userId = UUID.randomUUID().toString();
			uuidList.add(userId);
			jdbcTemplate.update("insert into user(id,username,password)values(?,?,?)",userId,
					teacher.getTeacherAccount(),MD5Utils.md5("123456"));
			//添加用户角色为学生
			Role role = roleDao.getRoleByCode("teacher");
			jdbcTemplate.update("insert into user_role(user_id,role_id)values(?,?)",userId,role.getId());
		}

		/////////添加教师
		String sql = "insert into teacher(id,teacherAccount,teacherName,positionalTitles)values(?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				ps.setString(1, uuidList.get(i));
				ps.setString(2, tempList.get(i).getTeacherAccount());
				ps.setString(3, tempList.get(i).getTeacherName());
				ps.setString(4, tempList.get(i).getPositionalTitles());
			}
			
			@Override
			public int getBatchSize() {
				return tempList.size();
			}
		});

	}

	
	
	
	/**
	 * 
	 * 内部类:Teacher类结果集处理器
	 *
	 */
	private class RowMapperTeacher implements RowMapper<Teacher> {

		@Override
		public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
			Teacher teacher = new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setTeacherAccount(rs.getString("teacherAccount"));
			teacher.setTeacherName(rs.getString("teacherName"));
			teacher.setPositionalTitles(rs.getString("positionalTitles"));
			return teacher;
		}

	}




}
