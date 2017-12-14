package cn.bytd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IReportDao;
import cn.bytd.domain.Classes;
import cn.bytd.domain.Course;
import cn.bytd.domain.Report;

/**
 * 
 * 说明:报告
 * @author yangying
 * @version 1.0
 * @date 2017年12月13日 下午4:39:35
 *
 *
 */
@Repository(value="reportDao")
public class ReportDaoImpl implements IReportDao{

	private RowMapper<Report> rm = new RowMapperReport();//Report通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 增加报告
	 * @param report
	 * @param taskId
	 */
	public void addReport(Report report, long taskId,long studentId){
		if(this.getReportByTaskId(taskId,studentId)==null){
			jdbcTemplate.update("insert into report(reportName,reportAddress,uploadDate,studentId,taskId)values(?,?,?,?,?)",
				report.getReportName(),report.getReportAddress(),new Date(),studentId,taskId);
		}else {
			jdbcTemplate.update("update report set reportName=?,reportAddress=?,uploadDate=?,studentId=?,taskId=?",
				report.getReportName(),report.getReportAddress(),new Date(),studentId,taskId);
		}
		//上传之后修改任务状态为完成
		jdbcTemplate.update("update task set completeStatus = 1 where id = ?", taskId);
	};
	/**
	 * 根据任务Id和学生Id获取报告
	 */
	public Report getReportByTaskId(long taskId,long studentId){
		Report report = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			report = jdbcTemplate.queryForObject("select * from report where taskId = ? and studentId = ?",rm,taskId,studentId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return report;
	}
	
	/**
	 * 
	 * 内部类:Report类结果集处理器
	 *
	 */
	private class RowMapperReport implements RowMapper<Report> {

		@Override
		public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
			Report report = new Report();
			report.setId(rs.getLong("id"));
			report.setReportName(rs.getString("reportName"));
			report.setReportAddress(rs.getString("reportAddress"));
			report.setUploadDate(rs.getDate("uploadDate"));
			return report;
		}

	}

}
