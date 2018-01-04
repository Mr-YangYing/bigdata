package cn.bytd.service;

import java.util.List;

import cn.bytd.domain.Course;
import cn.bytd.domain.Student;
import cn.bytd.domain.Teacher;
import cn.bytd.queryPage.TeacherQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

public interface ICourseService {
	/**
	 * 普通查询
	 * @return
	 */
	List<Course> list();

	/**
	 * 高级查询
	 * @param qo 高级查询对象
	 * @return
	 */
	//List<Course> query(CourseQueryObject qo);

	/**
	 * 分页查询
	 * @param currentPage 当前第几页
	 * @param pageSize 每页最多显示多少条数据
	 * @return 封装好结果集和分页信息的所有数据
	 */
	PageResult queryPage(Integer currentPage, Integer pageSize);
	/**
	 * 高级查询+分页查询
	 * @param qo 封装了所有的包含条件的查询信息和翻页的信息(currentPage, pageSize)
	 * @return 封装好的包含高级查询的结果集和分页信息的所有数据
	 */
	PageResult query(IQueryObject qo);
	
	/**
	 * 根据id删除
	 */
     void delete(long id);
     /**
      * 根据id获取
      * @param id
      */
     Course getById(long id);
 	/**
 	 * 根据学生Id获取学生所选择的课程
 	 * @param studentId
 	 * @return
 	 */
 	List<Course> getByStudentId(String studentId);
     /**
 	 * 获取列名
 	 */
 	public List<String> getColumnName();
     /**
      * 修改
      * @param course
      */
 	
 	public void batchUpdate(List<Course> list);
/*	void update(Course course);*/
 	/**
 	 * 根据教师Id获取课程
 	 * @param id
 	 * @return
 	 */
	List<Course> getCoursesByTeacherId(String teacherId);
	/**
	 * 根据教师id获取除去该教师的其他教师的课程
	 * @param teacherId
	 * @return
	 */
	List<Course> getCoursesByOtherTeacherId(String teacherId);
	/**
	 * 关闭课程
	 * @param id
	 */
	void closeCourse(long id);
	/**
	 * 开启课程
	 * @param id
	 */
	void openCourse(long id);
	/**
	 * 配置班级
	 * @param ids
	 */
	void setClasses(Long[] ids,long courseId);
	
}
