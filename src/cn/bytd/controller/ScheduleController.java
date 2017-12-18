package cn.bytd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/schedule")
public class ScheduleController {
	
	/**
	 * 课程表列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(){
		ModelAndView md = new ModelAndView();
		md.setViewName("views/director/schedule");
		return md;
	}
	
	/**
	 * 配置课程表
	 * @return
	 */
	@RequestMapping(value="/configureSchedule")
	public ModelAndView configureSchedule(){
		ModelAndView md = new ModelAndView();
		md.setViewName("views/director/configureSchedule");
		return md;
	}
}
