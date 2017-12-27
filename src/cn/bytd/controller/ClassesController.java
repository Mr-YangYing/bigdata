package cn.bytd.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bytd.domain.Classes;
import cn.bytd.service.IClassesService;

/**
 * 
 * 说明:班级controller
 * @author yangying
 * @version 1.0
 * @date 2017年12月12日 下午7:46:10
 *
 *
 */
@Controller
@RequestMapping(value="/classes")
public class ClassesController {
	@Resource(name="classesService")
	private IClassesService classesService;
	
	/**
	 * 根据教师Id获取教师教的所有班级
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value="/getClassesByTeacherId")
	public ModelAndView getClassesByTeacherId(long teacherId){
		List<Classes> classesList =classesService.getClassesByTeacherId(teacherId);
		ModelAndView md = new ModelAndView();
		md.addObject("classesList", classesList);
		md.setViewName("/views/teacher/taskScore");
		return md;
	}
	
	/**
	 * 根据课程Id获取课程对应已经选择的所有班级
	 * 
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/getClassesByCourseId")
	@ResponseBody
	public List<Classes> getClassesByCourseId(long courseId){
		return classesService.getClassesByCourseId(courseId);
	};
	/**
	 * 根据课程Id获取课程对应未选择的所有班级
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/getClassesNotInCourseId")
	@ResponseBody
	public List<Classes> getClassesNotInCourseId(long courseId){
		return classesService.getClassesNotInCourseId(courseId);
	};
	
	/**
	 * 获取所有班级
	 * @return
	 */
	@RequestMapping(value="/getClassesList")
	@ResponseBody
	public List<Classes> getClassesList(){
		List<Classes> classesList =classesService.getClassesList();
		return classesList;
	}
	
}
