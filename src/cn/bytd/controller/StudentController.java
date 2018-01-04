package cn.bytd.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import cn.bytd.domain.Task;
import cn.bytd.queryPage.StudentQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.ICourseService;
import cn.bytd.service.IStudentService;
import cn.bytd.service.ITaskService;

/**
 * 
 * 说明:学生Controller
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:20:18
 *
 */
@RequestMapping("/student")
@Controller
public class StudentController {

	@Autowired
	private IStudentService studentService;
	@Resource(name="courseService")
	private ICourseService courseService;
	@Resource(name="taskService")
	private ITaskService taskService;
	
	/**
	 * 列表
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,StudentQueryObject qo){
		//可以获取另一个方法传入的值
/*		
		Map<String, Object> modelMap = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		 if (modelMap!=null) {
			 int currentPage = (int) modelMap.get("currentPage");
			 qo.setCurrentPage(currentPage);
		}*/
		PageResult pageResult = studentService.query(qo);
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/director/student");
		return md;
	}
	/**
	 * 删除
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public ModelAndView delete(String id){
		studentService.delete(id);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/student/list");
		return md;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/batchDelete",method={RequestMethod.GET})
	public ModelAndView batchDelete(String[] ids){
		studentService.batchDelete(ids);
		return null;
	}
	/**
	 * 批量设置班级
	 * @param ids
	 * @param classesId
	 * @return
	 */
	@RequestMapping(value="/batchSetClasses",method={RequestMethod.GET})
	public ModelAndView batchSetClasses(String[] ids,long classesId){
		studentService.batchSetClasses(ids,classesId);
		return null;
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET})
	@ResponseBody
	public Student get(String id){
		return studentService.getById(id);
	}
	
	/**
	 * 根据班级Id获取学生
	 * @param classesId
	 * @return
	 */
	@RequestMapping(value="/getStudentByClassesId")
	@ResponseBody
	public List<Student> getStudentByClassesId(long classesId){
		return studentService.getStudentByClassesId(classesId);
	}
	
	/**
	 * 修改或添加
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(Student student/*,int currentPage,RedirectAttributes ra*/){
		ModelAndView md = new ModelAndView();
		if (!student.getId().equals("-1")) {
			studentService.update(student);
		}else {
			studentService.insert(student);
		}
		//ra.addFlashAttribute("currentPage", currentPage);
		md.setViewName("redirect:/student/list");
		return md;
	}
	
	/**
	 * 获取列名
	 * @return
	 */
	@RequestMapping(value="/getColumnName")
	@ResponseBody
	public List<String> getColumnName(){
		return studentService.getColumnName();
	}
	/**
	 * 根据学生Id获取该学生选择的课程
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value="/courseList")
	public ModelAndView courseList(String studentId){
		List<Course> list = courseService.getByStudentId(studentId);
		ModelAndView md = new ModelAndView();
		md.addObject("list",list);
		md.setViewName("views/student/course");
		return md;
	}
	
	/**
	 * 根据课程Id,查询课程以及该课程下已经发布的任务
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/courseTaskDetail",method=RequestMethod.GET)
	public ModelAndView courseTaskDetail(long courseId){
		
		Course course = courseService.getById(courseId);
		List<Task> publishedTaskList = taskService.getPublishedTaskByCourseId(courseId);//根据课程Id获取任务,而且是已经发布的任务
		
		ModelAndView md = new ModelAndView();
		md.addObject("course", course);
		md.addObject("publishedTaskList", publishedTaskList);
		md.setViewName("views/student/courseTaskDetail");
		return md;
	}
	
	/**
	 * 转向student管理页面
	 * @return
	 */
	@RequestMapping(value="/toStuMS")
	public ModelAndView toStuMS(){
		ModelAndView md = new ModelAndView();
		md.setViewName("views/student/course");
		return md;
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
		List<Student> studentList = new ArrayList<>();//存放课程表数据
		
		JSONArray excelDataArrays = (JSONArray) JSON.parse(excelData);//页面传入的Excel表的所有数据
		int arraySize = excelDataArrays.size();
		JSONArray databaseFiledArray = (JSONArray) JSON.parse(databaseFiled);//页面传入的选中的数据库字段数据
		for (int i = 0; i < arraySize ; i++) {
			JSONArray excelDataArray = (JSONArray) excelDataArrays.get(i);
			Student student = new Student();
			for (int j = 0; j < excelDataArray.size(); j++) {
				if (databaseFiledArray.get(j).toString().equals(Student.class.getDeclaredField("studentNumber").getName())) {
					student.setStudentNumber((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Student.class.getDeclaredField("studentName").getName())) {
					student.setStudentName((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Student.class.getDeclaredField("currentTerm").getName())) {
					student.setCurrentTerm((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Student.class.getDeclaredField("college").getName())) {
					student.setCollege((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Student.class.getDeclaredField("profession").getName())) {
					student.setProfession((String)excelDataArray.get(j));
				}

			}
			studentList.add(student);
		}
		System.out.println(studentList);
		studentService.batchUpdate(studentList);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/student/list");
		return md;
	}
	
}
