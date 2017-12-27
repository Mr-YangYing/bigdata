package cn.bytd.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.bytd.domain.Course;
import cn.bytd.domain.Student;
import cn.bytd.queryPage.CourseQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.ICourseService;
import cn.bytd.service.IStudentService;
import cn.bytd.util.DateUtil;

/**
 * 
 * 说明:课程Controller
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:20:18
 *
 */
@RequestMapping("/course")
@Controller
public class CourseController {

	@Autowired
	private ICourseService courseService;
	@Autowired
	private IStudentService studentService;
	
	/**
	 * 列表
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,CourseQueryObject qo){
		//可以获取另一个方法传入的值
/*		
		Map<String, Object> modelMap = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		 if (modelMap!=null) {
			 int currentPage = (int) modelMap.get("currentPage");
			 qo.setCurrentPage(currentPage);
		}*/
		PageResult pageResult = courseService.query(qo);
		
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/director/course");
		return md;
	}
	/**
	 * 删除
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public ModelAndView delete(long id){
		courseService.delete(id);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/course/list");
		return md;
	}
	/**
	 * 根据课程id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET})
	@ResponseBody
	public Course get(long id){
		return courseService.getById(id);
	}
	
	/**
	 * 根据教师id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getCoursesByTeacherId",method={RequestMethod.GET})
	@ResponseBody
	public List<Course> getCoursesByTeacherId(long teacherId){
		return courseService.getCoursesByTeacherId(teacherId);
	}
	/**
	 * 根据教师id获取除去该教师的其他教师的课程
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getCoursesByOtherTeacherId",method={RequestMethod.GET})
	@ResponseBody
	public List<Course> getCoursesByOtherTeacherId(long teacherId){
		return courseService.getCoursesByOtherTeacherId(teacherId);
	}
	
	/**
	 * 根据学生id获取课程
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getCourseByStudentId",method={RequestMethod.GET})
	@ResponseBody
	public List<Course> getCourseByStudentId(long studentId){
		return courseService.getByStudentId(studentId);
	}
	
	
	
	/**
	 * 关闭课程
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/closeCourse",method={RequestMethod.GET})
	public ModelAndView closeCourse(long id){
		ModelAndView md = new ModelAndView();
		courseService.closeCourse(id);
		md.setViewName("redirect:/course/list");
		return md;
	}
	/**
	 * 关闭课程
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/openCourse",method={RequestMethod.GET})
	public ModelAndView openCourse(long id){
		ModelAndView md = new ModelAndView();
		courseService.openCourse(id);
		md.setViewName("redirect:/course/list");
		return md;
	}

	/**
	 * 获取列名
	 * @return
	 */
	@RequestMapping(value="/getColumnName")
	@ResponseBody
	public List<String> getColumnName(){
		return courseService.getColumnName();
	}
	/**
	 * 批量添加
	 * @param list
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ParseException 
	 */
	@RequestMapping(value="/batchUpdate")
	public ModelAndView batchUpdate(@RequestParam(value="excelData") String excelData,@RequestParam(value="databaseFiled") String databaseFiled) throws NoSuchFieldException, SecurityException, ParseException{
		List<Course> courseList = new ArrayList<>();//存放课程表数据
		
		JSONArray excelDataArrays = (JSONArray) JSON.parse(excelData);//页面传入的Excel表的所有数据
		int arraySize = excelDataArrays.size();
		JSONArray databaseFiledArray = (JSONArray) JSON.parse(databaseFiled);//页面传入的选中的数据库字段数据
		for (int i = 0; i < arraySize ; i++) {
			JSONArray excelDataArray = (JSONArray) excelDataArrays.get(i);
			Course course = new Course();
			for (int j = 0; j < excelDataArray.size(); j++) {
				if (databaseFiledArray.get(j).toString().equals(Course.class.getDeclaredField("courseName").getName())) {
					course.setCourseName((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Course.class.getDeclaredField("startDate").getName())) {
					course.setStartDate(DateUtil.string2date(excelDataArray.get(j).toString(),"yyyy-MM-dd"));
				}
				if (databaseFiledArray.get(j).toString().equals(Course.class.getDeclaredField("endDate").getName())) {
					course.setEndDate(DateUtil.string2date(excelDataArray.get(j).toString(),"yyyy-MM-dd"));
				}
				if (databaseFiledArray.get(j).toString().equals(Course.class.getDeclaredField("teacherName").getName())) {
					course.setTeacherName((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Course.class.getDeclaredField("courseOpen").getName())) {
					course.setCourseOpen(excelDataArray.get(j).toString().equals("是")?1:0);
				}
				if (databaseFiledArray.get(j).toString().equals(Course.class.getDeclaredField("description").getName())) {
					course.setDescription((String)excelDataArray.get(j));
				}
			}
			courseList.add(course);
		}
		System.out.println(courseList);
		courseService.batchUpdate(courseList);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/course/list");
		return md;
	}
	/**
	 * 设置班级
	 * @return
	 */
	@RequestMapping(value="/setClasses")
	public ModelAndView setClasses(Long[] ids,long courseId){
		courseService.setClasses(ids,courseId);
		return null;
	}

	
/*	@RequestMapping(value="/update")
	public ModelAndView update(Course course,int currentPage,RedirectAttributes ra){
		courseService.update(course);
		ModelAndView md = new ModelAndView();
		//ra.addFlashAttribute("currentPage", currentPage);
		md.setViewName("redirect:/course/list");
		return md;
	}*/
}
