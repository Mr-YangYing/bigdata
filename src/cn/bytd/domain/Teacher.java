package cn.bytd.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 说明:教师实体类
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:20:03
 *
 */

public class Teacher {
	private long id;
	private String teacherAccount;//教师账号
	private String teacherName;//教师姓名
	private String positionalTitles;//职称
	
	private List<Student> students = new ArrayList<>();//教师对应的学生
	private List<Course> courses = new ArrayList<>();//教师对应的课程
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeacherAccount() {
		return teacherAccount;
	}
	public void setTeacherAccount(String teacherAccount) {
		this.teacherAccount = teacherAccount;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getPositionalTitles() {
		return positionalTitles;
	}
	public void setPositionalTitles(String positionalTitles) {
		this.positionalTitles = positionalTitles;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
