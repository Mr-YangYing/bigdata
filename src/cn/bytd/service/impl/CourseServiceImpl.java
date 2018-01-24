package cn.bytd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.ICourseDao;
import cn.bytd.dao.IStudentDao;
import cn.bytd.domain.Course;
import cn.bytd.domain.Student;
import cn.bytd.queryPage.StudentQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.ICourseService;
import cn.bytd.service.IStudentService;
import cn.bytd.service.IStudentService;
/**
 * 
 * 说明:课程业务类
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:21:32
 *
 */
@Service("courseService")
@Transactional
public class CourseServiceImpl implements ICourseService{
	@Autowired
	private ICourseDao courseDao;
	/**
	 * 普通查询
	 */
	public List<Course> list(){
		return courseDao.list();
	}
	/*public List<Course> query(CourseQueryObject qo){
		return courseDao.query(qo);
	}*/
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		return courseDao.queryPage(currentPage, pageSize);
	}
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return courseDao.query(qo);
	}
	/**
	 * 根据ID删除
	 */
	public void delete(long id) {
		courseDao.delete(id);
	}
	/**
	 * 根据id获取
	 */
	public Course getById(long id) {
		return courseDao.getById(id);
	}
	/**
 	 * 根据学生Id获取学生所选择的课程
 	 * @param studentId
 	 * @return
 	 */
 	public List<Course> getByStudentId(String studentId){
 		return courseDao.getByStudentId(studentId);
 	};
 	/**
 	 * 根据教师Id获取课程
 	 * @param id
 	 * @return
 	 */
	public List<Course> getCoursesByTeacherId(String teacherId){
		return courseDao.getCoursesByTeacherId(teacherId);
	}
	/**
	 * 获取未选的课程
	 * @param teacherId
	 * @return
	 */
	public List<Course> getCoursesByOtherTeacherId(){
		return courseDao.getCoursesByOtherTeacherId();
	};
	/**
	 * 根据教师id获取除去该教师的其他教师的课程
	 * @param teacherId
	 * @return
	 */
	public List<Course> getCoursesByOtherTeacherId(String teacherId){
		return courseDao.getCoursesByOtherTeacherId(teacherId);
	};
	
	/**
	 * 获取列名
	 */
	public List<String> getColumnName(){
		return courseDao.getColumnName();
	}
	/**
	 * 修改
	 */
/*	public void update(Course course) {
		courseDao.update(course);
	}*/
	@Override
	public void batchUpdate(List<Course> list) {
		 courseDao.batchUpdate(list);
	}
	
	/**
	 * 关闭课程
	 * @param id
	 */
	public void closeCourse(long id){
		courseDao.closeCourse(id);
	};
	
	/**
	 * 开启课程
	 * @param id
	 */
	public void openCourse(long id){
		courseDao.openCourse(id);
	};
	
	/**
	 * 配置班级
	 * @param ids
	 */
	public void setClasses(Long[] ids,long courseId){
		courseDao.setClasses(ids,courseId);
	}
	@Override
	public List<Course> getCourseByStudentId(String studentId) {
		return courseDao.getCourseByStudentId(studentId);
	};
}
