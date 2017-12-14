package cn.bytd.domain;

import java.util.Date;

/**
 * 
 * 说明:学生上传的报告/作业
 * @author yangying
 * @version 1.0
 * @date 2017年12月13日 下午3:30:37
 *
 *
 */
public class Report {
	private Long id;
	private String reportName;//报告名称
	private String reportAddress;//报告地址
	private Date uploadDate;//上传时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportAddress() {
		return reportAddress;
	}
	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
}
