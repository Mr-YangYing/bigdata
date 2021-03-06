package cn.bytd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IMarkDao;
import cn.bytd.dao.ITaskDao;
import cn.bytd.domain.Student;
import cn.bytd.domain.Task;
import cn.bytd.domain.TaskCompleteStatus;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;

/**
 * 
 * 说明:任务Dao实现
 * @author yangying
 * @version 1.0
 * @date 2017-11-20 下午1:22:05
 *
 */
@Repository("taskDao")
public class TaskDaoImpl implements ITaskDao{
	
	private RowMapper<Task> rm = new RowMapperTask(); 
 	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Resource(name="markDao")
	private IMarkDao markDao;
	
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "task", rm, jdbcTemplate);
	}

	/**
	 * 根据courseID获取任务
	 * @param courseId
	 * @return
	 */
	public List<Task> getTaskByCourseId(long courseId){
		return jdbcTemplate.query("select * from task where courseId = ?", rm, courseId);
	};
	
	/**
	 * 根据课程Id获取任务,而且是已经发布的任务
	 * @param courseId
	 * @return
	 */
	public List<Task> getPublishedTaskByCourseId(long courseId){
		return jdbcTemplate.query("select * from task where courseId = ? and publishTask = 1", rm,courseId);
	};
	
	@Override
	public List<Task> getTaskByCourseStudentId(long courseId, String studentId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select DISTINCT tk.*,taskcompletestatus.taskStatus from "
				+ "(select t.id,t.taskName,t.taskType,t.uploadReport,t.description,t.difficulty,t.publishTask,s.studentId from task as t right join "
				+ "(select * from report where studentId=?) as s "
				+ "on t.id=s.taskId where t.courseId=? and publishTask=1) as tk "
				+ "LEFT JOIN taskcompletestatus on tk.studentId=taskcompletestatus.studentId;",
				new RowMapper() {
					@Override
					public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
						Task task = new Task();
						task.setId(rs.getLong("id"));
						task.setTaskName(rs.getString("taskName"));
						task.setTaskType(rs.getInt("taskType"));
						task.setUploadReport(rs.getInt("uploadReport"));
						task.setDescription(rs.getString("description"));
						task.setPublishTask(rs.getInt("publishTask"));
						task.setDifficulty(rs.getInt("difficulty"));
						TaskCompleteStatus taskCompleteStatus = new TaskCompleteStatus();
						taskCompleteStatus.setTaskStatus(rs.getInt("taskStatus"));
						task.setTaskCompleteStatus(taskCompleteStatus);
						return task;
					}
				},studentId,courseId);
	}
	
	/**
	 * 根据任务Id获取任务
	 * @param taskId
	 * @return
	 */
	public Task getTaskById(long taskId){
		Task task = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			task = jdbcTemplate.queryForObject("select * from task where id = ?",rm,taskId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return task;
	};
	
	/**
	 * 更新任务
	 * @param task
	 */
    public void update(Task task){
		jdbcTemplate.update("update task set taskName = ?,taskType = ?,uploadReport = ?,publishTask = ?,difficulty = ?,description = ? where id = ?", 
				task.getTaskName(),task.getTaskType(),task.getUploadReport(),0,task.getDifficulty(),
				task.getDescription(),task.getId());
	};
	
	/**
	 * 插入任务
	 * @param task
	 */
	public void insert(Task task,long courseId){
		jdbcTemplate.update("insert into task(taskName,taskType,uploadReport,publishTask,difficulty,description,courseId) values(?,?,?,?,?,?,?,?)",
				task.getTaskName(),task.getTaskType(),task.getUploadReport(),0,task.getDifficulty(),
				task.getDescription(),courseId);
	};
	
	/**
	  * 发布任务
	  * @param taskId
	  */
	 public void publishTask(long taskId){
		 jdbcTemplate.update("update task set publishTask = 1 where id = ?",taskId);
	 }
	
		/**
		 * 删除任务
		 * @param taskId
		 */
	 public void deleteTask(long taskId){
		 jdbcTemplate.update("delete from task where id = ?", taskId);
	 };
	 
	 
		/**
		 * 给指定任务打分
		 * @param taskId
		 */
	 public void setScoreByTaskId(Integer score,long taskId,String studentId){
		 if(markDao.getMarkById(studentId, taskId)==null){
			 jdbcTemplate.update("insert into mark(score,studentId,taskId)values(?,?,?)",score,studentId,taskId);
		 }
		 jdbcTemplate.update("update mark set score = ? where studentId = ? and taskId = ?",score,studentId,taskId);
	 };
	 
	/**
	 * 
	 * 说明:内部类,Task结果集处理类
	 * @author yangying
	 * @version 1.0
	 * @date 2017-11-20 下午1:32:52
	 *
	 */
	class RowMapperTask implements RowMapper<Task>{

		@Override
		public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
			Task task = new Task();
			task.setId(rs.getLong("id"));
			task.setTaskName(rs.getString("taskName"));
			task.setTaskType(rs.getInt("taskType"));
			task.setUploadReport(rs.getInt("uploadReport"));
			task.setDescription(rs.getString("description"));
			task.setPublishTask(rs.getInt("publishTask"));
			task.setDifficulty(rs.getInt("difficulty"));
			return task;
		}
		
	}

}
