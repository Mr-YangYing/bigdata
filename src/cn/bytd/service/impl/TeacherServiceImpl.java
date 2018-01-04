package cn.bytd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.ITeacherDao;
import cn.bytd.domain.Course;
import cn.bytd.domain.Teacher;
import cn.bytd.queryPage.TeacherQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.ITeacherService;
/**
 * 
 * 说明:教师业务类
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:21:32
 *
 */
@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements ITeacherService{
	@Autowired
	private ITeacherDao teacherDao;
	
	public List<Teacher> list(){
		return teacherDao.list();
	}
	/*public List<Teacher> query(TeacherQueryObject qo){
		return teacherDao.query(qo);
	}*/
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		return teacherDao.queryPage(currentPage, pageSize);
	}
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return teacherDao.query(qo);
	}
	/**
	 * 根据ID删除
	 */
	public void delete(String id) {
		teacherDao.delete(id);
	}
	/**
	 * 批量删除
	 */
	public void batchDelete(String[] ids){
		teacherDao.batchDelete(ids);
	}
	/**
	 * 根据id获取
	 */
	public Teacher getById(String id) {
		return teacherDao.getById(id);
	}
	/**
	 * 修改
	 */
	public void update(Teacher teacher) {
		teacherDao.update(teacher);
	}
	/**
	 * 添加
	 */
	public void insert(Teacher teacher){
		teacherDao.insert(teacher);
	}
	
	/**
	 * 获取列名
	 */
	public List<String> getColumnName(){
		return teacherDao.getColumnName();
	}
	/**
	 * 修改
	 */
/*	public void update(Course course) {
		courseDao.update(course);
	}*/
	@Override
	public void batchUpdate(List<Teacher> list) {
		 teacherDao.batchUpdate(list);
	}
}
