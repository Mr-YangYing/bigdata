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

import cn.bytd.dao.IMarkDao;
import cn.bytd.domain.Mark;

/**
 * 
 * 说明:分数
 * @author yangying
 * @version 1.0
 * @date 2017年12月14日 下午4:29:28
 *
 *
 */
@Repository(value="markDao")
public class MarkDaoImpl implements IMarkDao{
	private RowMapper<Mark> rm = new RowMapperMark(); 
 	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 根据任务Id和学生Id获取分数
	 * @param taskId
	 * @param studentId
	 * @return
	 */
	public Mark getMarkById(String studentId,Long taskId){
		Mark mark = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			mark = jdbcTemplate.queryForObject("select * from mark where studentId = ? and taskId = ?",rm,studentId,taskId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return mark;
	};
	/**
	 * 根据任务Id获取分数
	 * @param taskId
	 * @return
	 */
	public List<Mark> getMarkByTaskId(long taskId){
		return jdbcTemplate.query("select * from mark where taskId = ?", rm,taskId);
	}
	
	/**
	 * 
	 * 说明:内部类,Mark结果集处理类
	 * @author yangying
	 * @version 1.0
	 * @date 2017-11-20 下午1:32:52
	 *
	 */
	class RowMapperMark implements RowMapper<Mark>{

		@Override
		public Mark mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mark mark = new Mark();
			mark.setId(rs.getLong("id"));
			mark.setScore(rs.getInt("score"));
			mark.setStudentId(rs.getString("studentId"));
			mark.setTaskId(rs.getLong("taskId"));
			return mark;
		}
		
	}
}
