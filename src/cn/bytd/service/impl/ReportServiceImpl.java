package cn.bytd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.IReportDao;
import cn.bytd.domain.Report;
import cn.bytd.service.IReportService;
@Service(value="reportService")
@Transactional
public class ReportServiceImpl implements IReportService{
	@Resource(name="reportDao")
	private IReportDao reportDao;
	
	/**
	 * 上传报告
	 */
	public void addReport(Report report, long taskId,long studentId) {
		reportDao.addReport(report,taskId,studentId);
	}

	/**
	 * 根据任务Id获取报告
	 */
	public Report getReportByTaskId(long taskId,long studentId){
		return reportDao.getReportByTaskId(taskId,studentId);
	};
}
