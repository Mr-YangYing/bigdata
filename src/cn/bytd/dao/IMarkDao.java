package cn.bytd.dao;

import java.util.List;

import cn.bytd.domain.Mark;

/**
 * 
 * 说明:分数
 * 
 * @author yangying
 * @version 1.0
 * @date 2017年12月14日 下午4:28:58
 *
 *
 */
public interface IMarkDao {

	/**
	 * 根据任务Id和学生Id获取分数
	 * 
	 * @param taskId
	 * @param studentId
	 * @return
	 */
	Mark getMarkById(String studentId, Long taskId);
	
	/**
	 * 根据任务Id获取分数
	 * @param taskId
	 * @return
	 */
	 List<Mark> getMarkByTaskId(long taskId);
}
