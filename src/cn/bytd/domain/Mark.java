package cn.bytd.domain;
/**
 * 
 * 说明:成绩
 * @author yangying
 * @version 1.0
 * @date 2017年12月14日 上午11:56:46
 *
 *
 */
public class Mark {
	private Long id;
	private Integer score;
	private Long studentId;
	private Long taskId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	
}
