package cn.bytd.service;

import java.util.List;

import cn.bytd.domain.Task;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

/**
 * 
 * 说明:task接口
 * @author yangying
 * @version 1.0
 * @date 2017-11-20 下午1:18:47
 *
 */
public interface ITaskService {
	/**
	 * 高级查询+分页查询
	 */
	PageResult query(IQueryObject qo);

	/**
	 * 根据课程Id获取所有任务,
	 * @param courseId
	 * @return
	 */
	List<Task> getTaskByCourseId(long courseId);

	/**
	 * 根据课程Id获取任务,而且是已经发布的任务
	 * @param courseId
	 * @return
	 */
	List<Task> getPublishedTaskByCourseId(long courseId);

	/**
	 * 根据任务Id获取任务
	 * @param taskId
	 * @return
	 */
	Task getTaskById(long taskId);

	/**
	 * 更新任务
	 * @param task
	 */
	void update(Task task);

	/**
	 * 插入任务
	 * @param task
	 * @param courseId 
	 */
	void insert(Task task, long courseId);

	/**
	 * 发布任务
	 * @param taskId
	 */
	void publishTask(long taskId);

	/**
	 * 删除任务
	 * @param taskId
	 */
	void deleteTask(long taskId);
	/**
	 * 打分
	 * @param taskId
	 */
	void setScoreByTaskId(Integer score,long taskId,long studentId);
	/**
	 * 根据课程和学生查询任务
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	List<Task> getTaskByCourseStudentId(long courseId, String studentId);
}
