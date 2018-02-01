package cn.bytd.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bytd.domain.Classes;
import cn.bytd.domain.Course;
import cn.bytd.domain.Mark;
import cn.bytd.domain.Student;
import cn.bytd.domain.Task;
import cn.bytd.queryPage.CourseQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.IClassesService;
import cn.bytd.service.ICourseService;
import cn.bytd.service.IMarkService;
import cn.bytd.service.IStudentService;
import cn.bytd.service.ITaskService;

/**
 * 
 * 说明:任务controller
 * @author yangying
 * @version 1.0
 * @date 2017-11-20 上午11:36:28
 *
 */
@Controller
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ICourseService courseService;
	@Resource(name="classesService")
	private IClassesService classesService;
	@Resource(name="markService")
	private IMarkService markService;
	@Autowired
	private IStudentService studentServie;
	/**
	 * 课程列表,包含课程对应的任务数
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping("/courseList")
	public ModelAndView coureList(CourseQueryObject qo){
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
		md.setViewName("views/teacher/course");
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
		md.setViewName("views/teacher/courseTaskDetail");
		return md;
	}
	/**
	 * 根据课程Id,查询课程以及该课程下所有的任务
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/courseTaskEdit",method=RequestMethod.GET)
	public ModelAndView courseTaskEdit(long courseId){
		
		Course course = courseService.getById(courseId);
		List<Task> taskList = (List<Task>) taskService.getTaskByCourseId(courseId);//根据课程Id获取所有任务
		
		ModelAndView md = new ModelAndView();
		md.addObject("course", course);
		md.addObject("taskList", taskList);
		md.setViewName("views/teacher/courseTaskEdit");
		return md;
	}
	/**
	 * 根据任务Id查询任务
	 * @param taskId
	 * @return json格式Task
	 */
	@RequestMapping(value="/getTaskById")
	@ResponseBody
	public Task getTaskById(long taskId){
	    return taskService.getTaskById(taskId);
	}
	/**
	 * 给指定任务打分数
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value="/setScoreByTaskId")
	public ModelAndView setScoreByTaskId(Integer score,long taskId,String studentId){
		taskService.setScoreByTaskId(score,taskId,studentId);
		return null;
	}
	
	/**
	 * 更新任务
	 * @return
	 */
	@RequestMapping(value="/updateTask")
	public ModelAndView updateTask(Task task,long courseId){
		if (task.getId()!=-1) {
			taskService.update(task);//修改任务
		} else {
			taskService.insert(task,courseId);//添加任务
		}
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/task/courseTaskEdit?courseId="+courseId);
		return md;
	}
	/**
	 * 发布任务
	 * @param taskId
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/publishTask")
	public ModelAndView publishTask(long taskId,long courseId){
		taskService.publishTask(taskId);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/task/courseTaskEdit?courseId="+courseId);
		return md;
	}
	/**
	 * 删除任务
	 * @param taskId
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/deleteTask")
	public ModelAndView deleteTask(long taskId,long courseId){
		taskService.deleteTask(taskId);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/task/courseTaskEdit?courseId="+courseId);
		return md;
	}
	
	
	/**
	 * 根据课程id获取所有任务,返回json
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getTaskByCourseId",method={RequestMethod.GET})
	@ResponseBody
	public List<Task> getTaskByCourseId(long courseId){
		return taskService.getTaskByCourseId(courseId);
	}
	/**
	 * 根据课程id获取所有任务,教师Id用于返回班级列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/taskList")
	public ModelAndView taskList(long courseId,String teacherId,String studentId,long classesId){
		List<Task> taskList = taskService.getTaskByCourseStudentId(courseId,studentId);
		List<Course> courseList =courseService.getCoursesByTeacherId(teacherId);
		List<Classes> classesList = classesService.getClassesByCourseId(courseId);
		List<Student> studentList = studentServie.getStudentByClassesId(classesId);
		for (int i = 0; i < taskList.size(); i++) {
			long taskId = taskList.get(i).getId();
			Mark mark = markService.getMarkById(studentId, taskId);
			taskList.get(i).setMark(mark);
		}
		ModelAndView md = new ModelAndView();
		md.addObject("taskList", taskList);
		md.addObject("courseList", courseList);
		md.addObject("classesList", classesList);
		md.addObject("studentList", studentList);
		md.addObject("classesId", classesId);
		md.addObject("courseId", courseId);
		md.addObject("studentId", studentId);
		md.setViewName("/views/teacher/taskScore");
		return md;
	}
	/**
	 * 根据课程id获取所有已经发布的任务
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getPublishedTaskByCourseId",method={RequestMethod.GET})
	@ResponseBody
	public List<Task> getPublishedTaskByCourseId(long courseId){
		return taskService.getPublishedTaskByCourseId(courseId);
	}
}



