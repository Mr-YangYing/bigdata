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

import cn.bytd.domain.Laboratory;
import cn.bytd.queryPage.LaboratoryQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.ILaboratoryService;

/**
 * 
 * 说明:实验室Controller
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:20:18
 *
 */
@RequestMapping("/laboratory")
@Controller
public class LaboratoryController {

	@Autowired
	private ILaboratoryService laboratoryService;
	
	/**
	 * 列表
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,LaboratoryQueryObject qo){
		//可以获取另一个方法传入的值
/*		
		Map<String, Object> modelMap = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		 if (modelMap!=null) {
			 int currentPage = (int) modelMap.get("currentPage");
			 qo.setCurrentPage(currentPage);
		}*/
		PageResult pageResult = laboratoryService.query(qo);
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/director/laboratory");
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
		laboratoryService.delete(id);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/laboratory/list");
		return md;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/batchDelete",method={RequestMethod.GET})
	public ModelAndView batchDelete(Long[] ids){
		laboratoryService.batchDelete(ids);
		return null;
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET})
	@ResponseBody
	public Laboratory get(long id){
		return laboratoryService.getById(id);
	}
	
	/**
	 * 修改或添加
	 * @param Laboratory
	 * @return
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(Laboratory Laboratory/*,int currentPage,RedirectAttributes ra*/){
		ModelAndView md = new ModelAndView();
		if (Laboratory.getId()!=-1) {
			laboratoryService.update(Laboratory);
		}else {
			laboratoryService.insert(Laboratory);
		}
		//ra.addFlashAttribute("currentPage", currentPage);
		md.setViewName("redirect:/laboratory/list");
		return md;
	}
	
	/**
	 * 获取列名
	 * @return
	 */
	@RequestMapping(value="/getColumnName")
	@ResponseBody
	public List<String> getColumnName(){
		return laboratoryService.getColumnName();
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
		List<Laboratory> laboratoryList = new ArrayList<>();//存放课程表数据
		
		JSONArray excelDataArrays = (JSONArray) JSON.parse(excelData);//页面传入的Excel表的所有数据
		System.out.println("页面传入的Excel表的所有数据"+excelDataArrays.size());
		int arraySize = excelDataArrays.size();
		JSONArray databaseFiledArray = (JSONArray) JSON.parse(databaseFiled);//页面传入的选中的数据库字段数据
		System.out.println("页面传入的选中的数据库字段数据"+databaseFiledArray.size());
		for (int i = 0; i < arraySize ; i++) {
			JSONArray excelDataArray = (JSONArray) excelDataArrays.get(i);
			Laboratory Laboratory = new Laboratory();
			for (int j = 0; j < excelDataArray.size(); j++) {
				if (databaseFiledArray.get(j).toString().equals(Laboratory.class.getDeclaredField("labAddress").getName())) {
					Laboratory.setLabAddress((String)excelDataArray.get(j));
				}
			}
			laboratoryList.add(Laboratory);
		}
		System.out.println(laboratoryList);
		laboratoryService.batchUpdate(laboratoryList);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/laboratory/list");
		return md;
	}
	
}
