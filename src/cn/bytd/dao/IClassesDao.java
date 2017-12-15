package cn.bytd.dao;

import java.util.List;

import cn.bytd.domain.Classes;

/**
 * 
 * 说明:班级接口
 * @author yangying
 * @version 1.0
 * @date 2017年12月12日 下午8:05:45
 *
 *
 */
public interface IClassesDao {
	/**
	 * 根据教师Id获取教师教的所有班级
	 * @param teacherId
	 * @return
	 */
	List<Classes> getClassesByTeacherId(long teacherId);
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
