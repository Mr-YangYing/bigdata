package cn.bytd.service;

import java.util.List;

import cn.bytd.domain.Classes;

/**
 * 
 * 说明:班级接口
 * @author yangying
 * @version 1.0
 * @date 2017年12月12日 下午7:56:55
 *
 *
 */
public interface IClassesService {
	/**
	 * 根据教师Id获取教师教的所有班级
	 * @return
	 */
	List<Classes> getClassesByTeacherId(String teacherId);
	/**
	 * 根据课程Id获取课程对应已经选择的所有班级
	 * 
	 * @param courseId
	 * @return
	 */
	List<Classes> getClassesByCourseId(long courseId);
	/**
	 * 根据课程Id获取课程对应未选择的所有班级
	 * @param courseId
	 * @return
	 */
	List<Classes> getClassesNotInCourseId(long courseId);
	/**
	 * 获取所有班级
	 * @return
	 */
	List<Classes> getClassesList();
	/**
	 * 根据Id获取班级
	 * @param classesId
	 * @return
	 */
	Classes getClassesById(long classesId);

}
