package cn.bytd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IClassesDao;
import cn.bytd.domain.Classes;

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
	public List<Classes> getClassesByTeacherId(long teacherId) {
		return jdbcTemplate.query("select * from classes where id in(select cla_id from teacher_classes_config where tea_id = ?)", rm, teacherId);
	}

	/**
	 * 
	 * 内部类:Course类结果集处理器
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
