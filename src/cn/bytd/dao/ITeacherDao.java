package cn.bytd.dao;

import java.util.List;

import cn.bytd.domain.Course;
import cn.bytd.domain.Teacher;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

public interface ITeacherDao {
	/**
	 * 普通查询
	 * @return
	 */
	List<Teacher> list();

	/**
	 * 高级查询
	 * @param qo 高级查询对象
	 * @return
	 */
	//List<Teacher> query(TeacherQueryObject qo);

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
	 * @param id
	 */
	void delete(String id);
	/**
	 * 批量删除
	 * @param ids
	 */
	void batchDelete(String[] ids);
	/**
	 * 根据id获取
	 * @param id
	 */
	Teacher getById(String id);
	/**
	 * 修改
	 * @param teacher
	 */
	void update(Teacher teacher);
	/**
	 * 添加
	 * @param teacher
	 */
	void insert(Teacher teacher);
	/**
	 * 获取所有列名
	 * @return
	 */
	List<String> getColumnName();
	
	/**
	 * 批量增加
	 */
	void batchUpdate(List<Teacher> list);
	/**
	 *根据账户获取教师
	 * @param teacherAccount
	 * @return
	 */
	Teacher getByTeacherAccount(String teacherAccount);
	/**
	 * 配置课程
	 * @param ids
	 * @param teacherId
	 */
	void setCourses(Long[] ids, String teacherId);
	/**
	 *通过课程Id获取教师
	 * @param long
	 * @return
	 */
	List<Teacher> getTeacherByCourseId(long courseId);
}
