package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IClassesDao;
import cn.bytd.dao.ICourseDao;
import cn.bytd.dao.ILaboratoryDao;
import cn.bytd.dao.ITaskDao;
import cn.bytd.dao.ITeacherDao;
import cn.bytd.domain.Course;
import cn.bytd.domain.Laboratory;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;

/**
 * 
 * 说明:课程Dao
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:22:04
 *
 */
@Repository("courseDao")
public class CourseDaoImpl implements ICourseDao {

	private RowMapper<Course> rm = new RowMapperCourse();//Course通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	private ITaskDao taskDao;
	@Autowired
	private IClassesDao classesDao;
	@Autowired
	private ITeacherDao teacherDao;
	
	@Resource(name="laboratoryDao")
	private ILaboratoryDao laboratoryDao;
	
	/**
	 * 查询所有
	 */
	public List<Course> list() {
		return jdbcTemplate.query("select * from course", rm, new Object[] {});
	}

	/**
	 * 高级查询
	 * @return
	 */
	/*public List<Course> query(CourseQueryObject qo) {
		String sql = "select * from course" + qo.getQuery();
		return jdbcTemplate.query(sql, rm, qo.getParameters().toArray());
	}*/
	

	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "course", rm,jdbcTemplate);
	}


	/**
	 * 分页查询
	 */
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		//查询结果集,得到listData
		String baseSql = "select * from teacher limit ?,?";
		List<Course> listData = jdbcTemplate.query(baseSql, rm, (currentPage - 1) * pageSize, pageSize);
		//查询结果总数,得到totalCount
		String countSql = "select count(*) from course";
		int count = jdbcTemplate.queryForObject(countSql, Integer.class);
		
		return new PageResult(listData, Integer.valueOf(count), currentPage, pageSize);
	}


	/**
	 * 根据id删除
	 */
	public void delete(long id) {
		jdbcTemplate.update("delete from course where id = ?", id);
	}



	/**
	 * 根据id获取
	 */
	public Course getById(long id) {
		Course course = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			course = jdbcTemplate.queryForObject("select * from course where id = ?",rm,id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return course;
	}

	/**
	 * 根据学生Id获取学生所选择的课程
	 * @param studentId
	 * @return
	 */
	public List<Course> getByStudentId(String studentId){
		return jdbcTemplate.query("SELECT * from course WHERE id IN (select cou_id from course_student_config where stu_id = ?)", rm, studentId);
	};

	/**
	 * 根据教师Id获取课程
	 * @param id
	 * @return
	 */
	public List<Course> getCoursesByTeacherId(String teacherId){
		return jdbcTemplate.query("select * from course where id In (select cou_id from course_teacher_config where tea_id = ?)", rm, teacherId);
	};
	/**
	 * 根据教师id获取除去该教师的其他教师的课程
	 * @param id
	 * @return
	 */
	public List<Course> getCoursesByOtherTeacherId(String teacherId){
		return jdbcTemplate.query("select * from course where id not in (select cou_id from course_teacher_config where tea_id = ?)", rm, teacherId);
	};
	
	
	/**
	 * 获取列名
	 */
	public List<String> getColumnName() {
		List<String> list = new ArrayList<>();
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from course");
		SqlRowSetMetaData metaData = sqlRowSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 2; i <=columnCount; i++) {
			list.add(metaData.getColumnName(i));
		}
		return list;
	}

	/**
	 * 关闭课程
	 */
	@Override
	public void closeCourse(long id) {
		jdbcTemplate.update("update course set courseOpen = 0 where id = ?", id);
	}
	/**
	 * 开启课程
	 */
	@Override
	public void openCourse(long id) {
		jdbcTemplate.update("update course set courseOpen = 1 where id = ?", id);
	}

	/**
	 * 配置班级
	 * @param ids
	 */
	public void setClasses(Long[] ids,long courseId){
		// 先清空
		jdbcTemplate.update("delete from course_classes_config where cou_id = ?", courseId);

		if (ids != null && ids.length > 0){
			for (int i = 0; i < ids.length; i++) {
				jdbcTemplate.update("insert into course_classes_config(cou_id,cla_id)values(?,?)", courseId, ids[i]);
			}
		}
	};
	

	/**
	 * 批量增加
	 */
	public void batchUpdate(List<Course> list) {
		
		final List<Course> tempList = list;
		
		String sql = "insert into course(courseName,startDate,endDate,courseOpen,description)values(?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, tempList.get(i).getCourseName());
				ps.setDate(2,new java.sql.Date(tempList.get(i).getStartDate().getTime()));
				ps.setDate(3,new java.sql.Date(tempList.get(i).getEndDate().getTime()));
				ps.setInt(5, tempList.get(i).getCourseOpen());
				ps.setString(6, tempList.get(i).getDescription());
			}
			
			@Override
			public int getBatchSize() {
				return tempList.size();
			}
		});
	}


	/**
	 * 修改
	 */
/*	public void update(Course course) {
		jdbcTemplate.update("update course set teacherAccount = ?,positionalTitles = ? where id = ?",
				teacher.getTeacherAccount(),teacher.getPositionalTitles(),teacher.getId());
	}*/

	
	
	/**
	 * 
	 * 内部类:Course类结果集处理器
	 *
	 */
	private class RowMapperCourse implements RowMapper<Course> {

		@Override
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
			Course course = new Course();
			course.setId(rs.getLong("id"));
			course.setCourseName(rs.getString("courseName"));
			course.setStartDate(rs.getDate("startDate"));
			course.setEndDate(rs.getDate("endDate"));
			course.setCourseOpen(rs.getInt("courseOpen"));
			course.setDescription(rs.getString("description"));
			course.setTasks(taskDao.getTaskByCourseId(rs.getLong("id")));
			course.setClassess(classesDao.getClassesByCourseId(rs.getLong("id")));
			course.setTeachers(teacherDao.getTeacherByCourseId(rs.getLong("id")));
			return course;
		}

	}




}
