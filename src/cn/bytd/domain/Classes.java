package cn.bytd.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 说明:班级表
 * @author yangying
 * @version 1.0
 * @date 2017-10-27 上午10:47:09
 */
public class Classes {
	private Long id;
	private String classNumber;//班号
	
	private List<Course> courses = new ArrayList<>();//班级对应的课程
	private List<Student> students = new ArrayList<>();//班级对应的学生
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
