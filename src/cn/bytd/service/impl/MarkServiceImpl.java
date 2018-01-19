package cn.bytd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bytd.dao.IMarkDao;
import cn.bytd.domain.Mark;
import cn.bytd.service.IMarkService;
/**
 * 
 * 说明:分数
 * @author yangying
 * @version 1.0
 * @date 2017年12月14日 下午8:24:42
 *
 *
 */
@Service(value="markService")
public class MarkServiceImpl implements IMarkService{
	@Resource(name="markDao")
	private IMarkDao markDao;
	/**
	 * 根据任务Id和学生Id获取分数
	 * 
	 * @param taskId
	 * @param studentId
	 * @return
	 */
	public Mark getMarkById(String studentId, Long taskId){
		return markDao.getMarkById(studentId, taskId);
	};
	
	/**
	 * 根据任务Id获取分数
	 * @param taskId
	 * @return
	 */
	public List<Mark> getMarkByTaskId(long taskId){
		return markDao.getMarkByTaskId(taskId);
	}
}
