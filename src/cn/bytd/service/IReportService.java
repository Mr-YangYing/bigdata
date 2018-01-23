package cn.bytd.service;

import cn.bytd.domain.Report;

/**
 * 
 * 说明:报告
 * @author yangying
 * @version 1.0
 * @date 2017年12月13日 下午4:34:27
 *
 *
 */
public interface IReportService {
	/**
	 * 增加报告
	 * @param report
	 * @param taskId
	 * @param studentId
	 */
	void addReport(Report report, long taskId,String studentId);
	/**
	 * 根据任务Id获取报告
	 * @param taskId
	 * @param studentId
	 * @return
	 */
	Report getReportByTaskId(long taskId,String studentId);
}
