package cn.bytd.domain;
/**
 * 
 * 说明:任务完成状态
 * @author yangying
 * @version 1.0
 * @date 2018年3月27日 下午1:50:16
 *
 *
 */
public class TaskCompleteStatus {

	private Long id;
	private String studentId;
	private Long taskId;
	private Integer taskStatus;//完成状态  0:未完成,1:已完成
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
}
