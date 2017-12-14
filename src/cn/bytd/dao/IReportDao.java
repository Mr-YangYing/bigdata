package cn.bytd.dao;

import cn.bytd.domain.Report;

/**
 * 
 * 说明:报告接口
 * @author yangying
 * @version 1.0
 * @date 2017年12月13日 下午4:38:42
 *
 *
 */
public interface IReportDao {
	/**
	 * 增加报告
	 * @param report
	 * @param taskId
	 * @param studentId
	 */
	void addReport(Report report, long taskId,long studentId);
	/**
	 * 根据任务Id和学生Id获取报告
	 * @param taskId
	 * @param studentId
	 * @return
	 */
	Report getReportByTaskId(long taskId,long studentId);

}
