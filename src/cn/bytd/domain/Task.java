package cn.bytd.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 说明:课程任务
 * @author yangying
 * @version 1.0
 * @date 2017-11-20 上午10:32:39
 *
 */

public class Task {
	private long id;
	private String taskName;
	private Integer taskType;//0:实验,1:作业,2:学习
	private Integer uploadReport;//上传报告是否开启   0:关闭, 1:开启
	private Integer publishTask;//是否发布任务  0:未发布,1:发布
	private Integer difficulty;//完成难度 0:简单,1:一般,2:中等,3:难   
	private String description;
	
	private Course course;//任务对应的课程
	private List<Resource> resources = new ArrayList<>();//任务对应的资源
	private Mark mark;//任务对应的分数
	private TaskCompleteStatus taskCompleteStatus;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public Integer getUploadReport() {
		return uploadReport;
	}
	public void setUploadReport(Integer uploadReport) {
		this.uploadReport = uploadReport;
	}
	public Integer getPublishTask() {
		return publishTask;
	}
	public void setPublishTask(Integer publishTask) {
		this.publishTask = publishTask;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	public Mark getMark() {
		return mark;
	}
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	public TaskCompleteStatus getTaskCompleteStatus() {
		return taskCompleteStatus;
	}
	public void setTaskCompleteStatus(TaskCompleteStatus taskCompleteStatus) {
		this.taskCompleteStatus = taskCompleteStatus;
	}

	
}
