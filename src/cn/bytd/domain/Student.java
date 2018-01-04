package cn.bytd.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 说明:学生类
 * @author yangying
 * @version 1.0
 * @date 2017-10-27 上午9:46:55
 *
 */

public class Student {
	private String id;
	private String studentNumber;//学号
	private String studentName;//姓名
	private String currentTerm;//所在学期
	private String college;//学院
	private String profession;//专业
	
	private Classes classes;//学生对应的班级
	private List<Teacher> teachers = new ArrayList<>();//学生对应的教师
	private List<Course> courses = new ArrayList<>();//学生对应的课程
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(String currentTerm) {
		this.currentTerm = currentTerm;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	
}
