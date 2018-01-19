package cn.bytd.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.ITaskDao;
import cn.bytd.domain.Task;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.ITaskService;

/**
 * 
 * 说明:taskService实现
 * @author yangying
 * @version 1.0
 * @date 2017-11-20 下午1:19:32
 *
 */
@Service("taskService")
@Transactional
public class TaskServiceImpl implements ITaskService{

	@Autowired
	private ITaskDao taskDao;
	
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo){
		return taskDao.query(qo);
	}
	
	
	/**
	 * 根据课程Id获取任务
	 * @param courseId
	 * @return
	 */
	public List<Task> getTaskByCourseId(long courseId){
		return taskDao.getTaskByCourseId(courseId);
	};
	
	/**
	 * 根据课程Id获取任务,而且是已经发布的任务
	 * @param courseId
	 * @return
	 */
	public List<Task> getPublishedTaskByCourseId(long courseId){
		return taskDao.getPublishedTaskByCourseId(courseId);
	};
	
	/**
	 * 根据任务Id获取任务
	 * @param taskId
	 * @return
	 */
	public Task getTaskById(long taskId){
		return taskDao.getTaskById(taskId);
	};
	
	/**
	 * 更新任务
	 * @param task
	 */
	public void update(Task task){
		taskDao.update(task);
	};
	
	/**
	 * 插入任务
	 * @param task
	 */
	public void insert(Task task,long courseId){
		 taskDao.insert(task,courseId);
	 };
	 
	 /**
	  * 发布任务
	  * @param taskId
	  */
	 public void publishTask(long taskId){
		 taskDao.publishTask(taskId);
	 };
	 
		/**
		 * 删除任务
		 * @param taskId
		 */
	 public	void deleteTask(long taskId){
		 taskDao.deleteTask(taskId);
	 };
	 
		/**
		 * 打分
		 * @param taskId
		 */
	 public void setScoreByTaskId(Integer score,long taskId,long studentId){
		 taskDao.setScoreByTaskId(score,taskId,studentId);
	 }


		@Override
		public List<Task> getTaskByCourseStudentId(long courseId, String studentId) {
			// TODO Auto-generated method stub
			return taskDao.getTaskByCourseStudentId(courseId, studentId);
		};
}
