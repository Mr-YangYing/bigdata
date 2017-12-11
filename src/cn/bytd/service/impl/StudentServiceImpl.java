package cn.bytd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.IStudentDao;
import cn.bytd.domain.Course;
import cn.bytd.domain.Student;
import cn.bytd.queryPage.StudentQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.IStudentService;
import cn.bytd.service.IStudentService;
/**
 * 
 * 说明:学生业务类
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:21:32
 *
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements IStudentService{
	@Autowired
	private IStudentDao studentDao;
	
	public List<Student> list(){
		return studentDao.list();
	}
	/*public List<Student> query(StudentQueryObject qo){
		return studentDao.query(qo);
	}*/
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		return studentDao.queryPage(currentPage, pageSize);
	}
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return studentDao.query(qo);
	}
	/**
	 * 根据ID删除
	 */
	public void delete(long id) {
		studentDao.delete(id);
	}
	/**
	 * 批量删除
	 */
	public void batchDelete(Long[] ids){
		studentDao.batchDelete(ids);
	}
	/**
	 * 根据id获取
	 */
	public Student getById(long id) {
		return studentDao.getById(id);
	}
	/**
	 * 修改
	 */
	public void update(Student student) {
		studentDao.update(student);
	}
	/**
	 * 添加
	 */
	public void insert(Student student) {
		studentDao.insert(student);
	}
	/**
	 * 获取列名
	 */
	public List<String> getColumnName(){
		return studentDao.getColumnName();
	}
	/**
	 * 修改
	 */
/*	public void update(Course course) {
		courseDao.update(course);
	}*/
	@Override
	public void batchUpdate(List<Student> list) {
		 studentDao.batchUpdate(list);
	}
}
