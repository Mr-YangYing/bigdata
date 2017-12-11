package cn.bytd.domain;

import java.util.Date;

/**
 * 
 * 说明:任务对应的资源
 * @author yangying
 * @version 1.0
 * @date 2017-11-22 下午4:14:02
 *
 */

public class Resource {
	private long id;
	private String resourceNumber;//资源编号
	private String resourceName;//资源名称
	private String resourceAddr;//资源上传地址
	private String uploader;//上传者
	private Date uploadDate;//上传时间
	private String description;//描述
	
	private Task task;//资源对应的任务

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getResourceNumber() {
		return resourceNumber;
	}

	public void setResourceNumber(String resourceNumber) {
		this.resourceNumber = resourceNumber;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceAddr() {
		return resourceAddr;
	}

	public void setResourceAddr(String resourceAddr) {
		this.resourceAddr = resourceAddr;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	
}
