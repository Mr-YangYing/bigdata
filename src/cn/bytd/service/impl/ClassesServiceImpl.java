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
	public List<Classes> getClassesByTeacherId(long teacherId) {
		return classesDao.getClassesByTeacherId(teacherId);
	}

}
