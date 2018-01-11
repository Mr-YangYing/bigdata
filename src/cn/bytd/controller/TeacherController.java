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

import cn.bytd.domain.Teacher;
import cn.bytd.domain.User;
import cn.bytd.queryPage.TeacherQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.ITeacherService;

/**
 * 
 * 说明:教师Controller
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:20:18
 *
 */
@RequestMapping("/teacher")
@Controller
public class TeacherController {

	@Autowired
	private ITeacherService teacherService;
	
	/**
	 * 列表
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,TeacherQueryObject qo){
		//可以获取另一个方法传入的值
/*		
		Map<String, Object> modelMap = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		 if (modelMap!=null) {
			 int currentPage = (int) modelMap.get("currentPage");
			 qo.setCurrentPage(currentPage);
		}*/
		PageResult pageResult = teacherService.query(qo);
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/director/teacher");
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
		teacherService.delete(id);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/teacher/list");
		return md;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/batchDelete",method={RequestMethod.GET})
	public ModelAndView batchDelete(String[] ids){
		teacherService.batchDelete(ids);
		return null;
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET})
	@ResponseBody
	public Teacher get(String id){
		return teacherService.getById(id);
	}
	
	/**
	 * 根据teacherAccount获取
	 * @param teacherAccount
	 * @return
	 */
	@RequestMapping(value="/checkTeacherAccount")
	@ResponseBody
	public String checkTeacherAccount(String teacherAccount){
		 Teacher teacher = teacherService.getByTeacherAccount(teacherAccount);
		 if(teacher!=null){
			 return "0";//用户已经存在
		 }else{
			 return "1";//用户不存在
		 }
	}
	
	/**
	 * 修改/添加
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(Teacher teacher/*,int currentPage,RedirectAttributes ra*/){
		ModelAndView md = new ModelAndView();
		if (!teacher.getId().equals("-1")) {
			teacherService.update(teacher);
		}else {
			teacherService.insert(teacher);
		}
		md.setViewName("redirect:/teacher/list");
		//ra.addFlashAttribute("currentPage", currentPage);方法之间传递参数
		return md;
	}
	
	/**
	 * 获取列名
	 * @return
	 */
	@RequestMapping(value="/getColumnName")
	@ResponseBody
	public List<String> getColumnName(){
		return teacherService.getColumnName();
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
		List<Teacher> teacherList = new ArrayList<>();//存放课程表数据
		
		JSONArray excelDataArrays = (JSONArray) JSON.parse(excelData);//页面传入的Excel表的所有数据
		int arraySize = excelDataArrays.size();
		JSONArray databaseFiledArray = (JSONArray) JSON.parse(databaseFiled);//页面传入的选中的数据库字段数据
		for (int i = 0; i < arraySize ; i++) {
			JSONArray excelDataArray = (JSONArray) excelDataArrays.get(i);
			Teacher teacher = new Teacher();
			for (int j = 0; j < excelDataArray.size(); j++) {
				if (databaseFiledArray.get(j).toString().equals(Teacher.class.getDeclaredField("teacherAccount").getName())) {
					teacher.setTeacherAccount((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Teacher.class.getDeclaredField("teacherName").getName())) {
					teacher.setTeacherName((String)excelDataArray.get(j));
				}
				if (databaseFiledArray.get(j).toString().equals(Teacher.class.getDeclaredField("positionalTitles").getName())) {
					teacher.setPositionalTitles((String)excelDataArray.get(j));
				}


			}
			teacherList.add(teacher);
		}
		System.out.println(teacherList);
		teacherService.batchUpdate(teacherList);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/teacher/list");
		return md;
	}
	
}
