package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import cn.bytd.dao.IStudentDao;
import cn.bytd.dao.ITeacherDao;
import cn.bytd.dao.IUserDao;
import cn.bytd.domain.Course;
import cn.bytd.domain.Resource;
import cn.bytd.domain.Role;
import cn.bytd.domain.Student;
import cn.bytd.domain.Teacher;
import cn.bytd.domain.User;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;
import cn.bytd.service.IClassesService;
import cn.bytd.util.MD5Utils;

/**
 * 
 * 说明:学生Dao
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:22:04
 *
 */
@Repository("studentDao")
public class StudentDaoImpl implements IStudentDao {

	private RowMapper<Student> rm = new RowMapperStudent();//Student通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@javax.annotation.Resource(name="classesService")
	private IClassesService classesService;
	
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;

	/**
	 * 查询所有
	 */
	public List<Student> list() {
		return jdbcTemplate.query("select * from student", rm, new Object[] {});
	}

	/**
	 * 高级查询
	 * @return
	 */
	/*public List<Student> query(StudentQueryObject qo) {
		String sql = "select * from student" + qo.getQuery();
		return jdbcTemplate.query(sql, rm, qo.getParameters().toArray());
	}*/
	

	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "student", rm,jdbcTemplate);
	}


	/**
	 * 分页查询
	 */
	@SuppressWarnings("deprecation")
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		//查询结果集,得到listData
		String baseSql = "select * from student limit ?,?";
		List<Student> listData = jdbcTemplate.query(baseSql, rm, (currentPage - 1) * pageSize, pageSize);
		//查询结果总数,得到totalCount
		String countSql = "select count(*) from student";
		int count = jdbcTemplate.queryForObject(countSql, Integer.class);
		
		return new PageResult(listData, Integer.valueOf(count), currentPage, pageSize);
	}


	public Student getByStudentNumber(String studentNumber){
		Student student = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			student = jdbcTemplate.queryForObject("select * from student where studentNumber = ?",rm,studentNumber);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return student;
	}
	
	/**
	 * 根据id删除
	 */
	public void delete(String id) {
		//删除关联表的学生
		jdbcTemplate.update("delete from course_student_config where stu_id = ?", id);
		jdbcTemplate.update("delete from student where id = ?", id);
		//删除学生对应的用户
		userDao.delete(id);
	}



	/**
	 * 批量设置学生班级
	 */
	public void batchSetClasses(String[] ids,long classesId) {
		final List<String> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		jdbcTemplate.batchUpdate("update student set classesId = ? where id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1,classesId);
				ps.setString(2,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
	}
	/**
	 * 批量删除
	 */
	public void batchDelete(String[] ids) {
		final List<String> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
			//删除学生用户
			userDao.delete(ids[i]);
		}
		//删除关联表的学生
		jdbcTemplate.batchUpdate("delete from course_student_config where stu_id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
		//删除学生
		jdbcTemplate.batchUpdate("delete from student where id = ?",new BatchPreparedStatementSetter() {
			
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
	public Student getById(String id) {
		Student student = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			student = jdbcTemplate.queryForObject("select * from student where id = ?",rm,id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return student;
	}
	
	
	/**
	 * 根据班级Id获取学生
	 * 
	 * @param classesId
	 * @return
	 */
	public List<Student> getStudentByClassesId(long classesId){
		return jdbcTemplate.query("select * from student where classesId = ?", rm,classesId);
	};

	/**
	 * 修改
	 */
	public void update(Student student) {
		//修改学生
		jdbcTemplate.update("update student set studentNumber = ?,studentName = ?,currentTerm = ?,college = ? where id = ?",
				student.getStudentNumber(),student.getStudentName(),student.getCurrentTerm(),
				student.getCollege(),student.getId());
		//修改学生用户
		User user = new User();
		user.setId(student.getId());
		user.setUsername(student.getStudentNumber());
		userDao.updateStudentUser(user);
	}


	/**
	 * 添加
	 */
	public void insert(Student student) {
		String userId = UUID.randomUUID().toString();
		jdbcTemplate.update("insert into student(id,studentNumber,studentName,currentTerm,college,profession)values(?,?,?,?,?,?)", 
				userId,student.getStudentNumber(),student.getStudentName(),student.getCurrentTerm(),
				student.getCollege(),student.getProfession());
		//添加用户
		jdbcTemplate.update("insert into user(id,username,password)values(?,?,?)",userId,
				student.getStudentNumber(),MD5Utils.md5("123456"));
		//添加用户角色为学生
		Role role = roleDao.getRoleByCode("student");
		jdbcTemplate.update("insert into user_role(user_id,role_id)values(?,?)",userId,role.getId());
	}

	/**
	 * 获取列名
	 */
	public List<String> getColumnName() {
		List<String> list = new ArrayList<>();
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from student");
		SqlRowSetMetaData metaData = sqlRowSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 2; i <=columnCount-1; i++) {
			list.add(metaData.getColumnName(i));
		}
		return list;
	}



	/**
	 * 批量增加
	 */
	public void batchUpdate(List<Student> list) {
		
		final List<Student> tempList = list;
		List<String> uuidList = new ArrayList<>();//存放id
		//////添加学生用户
		for (Student student : tempList) {
			String userId = UUID.randomUUID().toString();
			uuidList.add(userId);
			jdbcTemplate.update("insert into user(id,username,password)values(?,?,?)",userId,
					student.getStudentNumber(),MD5Utils.md5("123456"));
			//添加用户角色为学生
			Role role = roleDao.getRoleByCode("student");
			jdbcTemplate.update("insert into user_role(user_id,role_id)values(?,?)",userId,role.getId());
		}
		///////添加学生
		String sql = "insert into student(id,studentNumber,studentName,currentTerm,college,profession)values(?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, uuidList.get(i));
				ps.setString(2, tempList.get(i).getStudentNumber());
				ps.setString(3, tempList.get(i).getStudentName());
				ps.setString(4, tempList.get(i).getCurrentTerm());
				ps.setString(5, tempList.get(i).getCollege());
				ps.setString(6, tempList.get(i).getProfession());
			}
			
			@Override
			public int getBatchSize() {
				return tempList.size();
			}
		});

	}



	
	/**
	 * 
	 * 内部类:Student类结果集处理器
	 *
	 */
	private class RowMapperStudent implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getString("id"));
			student.setStudentNumber(rs.getString("studentNumber"));
			student.setStudentName(rs.getString("studentName"));
			student.setCurrentTerm(rs.getString("currentTerm"));
			student.setCollege(rs.getString("college"));
			student.setProfession(rs.getString("profession"));
			student.setClasses(classesService.getClassesById(rs.getLong("classesId")));
			return student;
		}

	}


}
