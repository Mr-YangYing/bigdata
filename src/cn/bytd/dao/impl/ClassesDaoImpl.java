package cn.bytd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IClassesDao;
import cn.bytd.domain.Classes;
import cn.bytd.domain.Student;

/**
 * 
 * 说明:班级Dao实现
 * @author yangying
 * @version 1.0
 * @date 2017年12月12日 下午8:08:10
 *
 *
 */
@Repository(value="classesDao")
public class ClassesDaoImpl implements IClassesDao{

	private RowMapper<Classes> rm = new RowMapperClasses();//Classes通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/**
	 * 根据教师Id获取教师教的所有班级
	 */
	public List<Classes> getClassesByTeacherId(String teacherId) {
		return jdbcTemplate.query("select * from classes where id in(select cla_id from teacher_classes_config where tea_id = ?)", rm, teacherId);
	}
	/**
	 * 根据课程Id获取课程对应已经选择的所有班级
	 */
	public List<Classes> getClassesByCourseId(long courseId) {
		return jdbcTemplate.query("select * from classes where id in(select cla_id from course_classes_config where cou_id = ?)", rm, courseId);
	}
	/**
	 * 根据课程Id获取课程对应未选择的所有班级
	 */
	public List<Classes> getClassesNotInCourseId(long courseId) {
		return jdbcTemplate.query("select * from classes where id not in(select cla_id from course_classes_config where cou_id = ?)", rm, courseId);
	}

	/**
	 * 获取所有班级
	 * @return
	 */
	public List<Classes> getClassesList(){
		return jdbcTemplate.query("select * from classes", rm);
	};
	
	

	@Override
	public Classes getClassesById(long classesId) {
		Classes classes = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			classes = jdbcTemplate.queryForObject("select * from classes where id = ?",rm,classesId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return classes;
	}

	
	/**
	 * 
	 * 内部类:Classes类结果集处理器
	 *
	 */
	private class RowMapperClasses implements RowMapper<Classes> {

		@Override
		public Classes mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classes classes = new Classes();
			classes.setId(rs.getLong("id"));
			classes.setClassesNumber(rs.getString("classesNumber"));
			return classes;
		}

	}
	
}
