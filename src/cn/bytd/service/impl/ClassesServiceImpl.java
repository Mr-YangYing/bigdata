package cn.bytd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.IClassesDao;
import cn.bytd.domain.Classes;
import cn.bytd.service.IClassesService;

/**
 * 
 * 说明:班级service
 * @author yangying
 * @version 1.0
 * @date 2017年12月12日 下午7:58:42
 *
 *
 */
@Service(value="classesService")
@Transactional
public class ClassesServiceImpl implements IClassesService{
	@Resource(name="classesDao")
	private IClassesDao classesDao;
	/**
	 * 根据教师Id获取教师教的所有班级
	 */
	public List<Classes> getClassesByTeacherId(String teacherId) {
		return classesDao.getClassesByTeacherId(teacherId);
	}
	
	/**
	 * 根据课程Id获取课程对应已经选择的所有班级
	 * 
	 * @param courseId
	 * @return
	 */
	public List<Classes> getClassesByCourseId(long courseId){
		return classesDao.getClassesByCourseId(courseId);
	};
	/**
	 * 根据课程Id获取课程对应未选择的所有班级
	 * @param courseId
	 * @return
	 */
	public List<Classes> getClassesNotInCourseId(long courseId){
		return classesDao.getClassesNotInCourseId(courseId);
	};
	
	/**
	 * 获取所有班级
	 * @return
	 */
	public List<Classes> getClassesList(){
		return classesDao.getClassesList();
	}

	/**
	 * 
	 */
	public Classes getClassesById(long classesId) {
		return classesDao.getClassesById(classesId);
	};

}
