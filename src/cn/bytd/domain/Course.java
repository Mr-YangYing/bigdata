package cn.bytd.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * 说明:课程表
 * @author yangying
 * @version 1.0
 * @date 2017-10-27 上午10:47:09
 */

public class Course {
	private long id;
	private String courseName;//课程名称
	private Date startDate;//开课日期
	private Date endDate;//节课日期
	private String teacherName;//课程对应老师姓名
	private int courseOpen;//是否开课 1:开课,0:未开课
	private String description;
	
	private Laboratory laboratory;//课程对应实验室
	private List<Teacher> teachers = new ArrayList<>();//课程对应老师
	private List<Student> students = new ArrayList<>();//课程对应学生
	private List<Task> tasks = new ArrayList<>();//课程对应任务
	private List<Classes> classess = new ArrayList<>();//课程对应班级
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getCourseOpen() {
		return courseOpen;
	}
	public void setCourseOpen(int courseOpen) {
		this.courseOpen = courseOpen;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public List<Classes> getClassess() {
		return classess;
	}
	public void setClassess(List<Classes> classess) {
		this.classess = classess;
	}
	public Laboratory getLaboratory() {
		return laboratory;
	}
	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

}
