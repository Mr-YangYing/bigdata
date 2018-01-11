package cn.bytd.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import cn.bytd.domain.Role;
import cn.bytd.domain.User;
import cn.bytd.queryPage.UserQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.IUserService;
import cn.bytd.util.MD5Utils;

/**
 * 
 * 说明:用户Controller
 * @author yangying
 * @version 1.0
 * @date 2017年12月26日 上午10:59:12
 *
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/login")
	public ModelAndView login(User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView md = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),MD5Utils.md5(user.getPassword()));
		User loginUser = null;
		try {
			subject.login(usernamePasswordToken);
			loginUser = (User) subject.getPrincipal();
			request.getSession().setAttribute("loginUser", loginUser);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			md.addObject("error", "用户名错误");
			md.setViewName("views/login");
			return md;
		} catch (IncorrectCredentialsException e){
			e.printStackTrace();
			System.out.println("密码错误");
			md.addObject("error", "密码错误");
			md.setViewName("views/login");
			return md;
		}
		Set<Role> roles = loginUser.getRoles();
		String permissionName = null;
		if(roles!=null){
			for (Role role : roles) {
				permissionName = role.getCode();
			}
			if("director".equals(permissionName)){
				md.setViewName("redirect:/teacher/list");
			}
			if("teacher".equals(permissionName)){
				md.setViewName("redirect:/task/courseList");
			}
			if("student".equals(permissionName)){
				md.setViewName("redirect:/student/courseList?studentId="+loginUser.getId());
			}
			if("admin".equals(permissionName)){
				md.setViewName("redirect:/permission/list");
			}
		}
		
		return md;
	}
	/**
	 * 登出
	 */
	@RequestMapping(value="/loginOut")
	public ModelAndView loginOut(HttpServletRequest request){
		request.getSession().invalidate();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/login");
		return modelAndView;
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/updatePassword")
	public String updatePassword(HttpServletRequest request,HttpServletResponse response,User user) throws IOException{
		String f = "1";
		//获取当前登录用户
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		try{
			userService.editPassword(loginUser.getId(),user.getPassword());
		}catch(Exception e){
			f = "0";
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(f);
		return null;

	}
	
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,UserQueryObject qo){
		//可以获取另一个方法传入的值
/*		
		Map<String, Object> modelMap = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		 if (modelMap!=null) {
			 int currentPage = (int) modelMap.get("currentPage");
			 qo.setCurrentPage(currentPage);
		}*/
		PageResult pageResult = userService.query(qo);
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/admin/user");
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
		userService.delete(id);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/user/list");
		return md;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/batchDelete",method={RequestMethod.GET})
	public ModelAndView batchDelete(String[] ids){
		userService.batchDelete(ids);
		return null;
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET})
	@ResponseBody
	public User get(String id){
		return userService.getById(id);
	}
	/**
	 * 根据username获取
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/checkUsername")
	@ResponseBody
	public String checkUsername(String username){
		 User user = userService.getByUsername(username);
		 if(user!=null){
			 return "0";//用户已经存在
		 }else{
			 return "1";//用户不存在
		 }
	}
	
	/**
	 * 修改或添加
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(User user,String[] roleIds/*,int currentPage,RedirectAttributes ra*/){
		ModelAndView md = new ModelAndView();
		if (!user.getId().equals("-1")) {
			try {
				userService.update(user);
			} catch (Exception e) {
				System.out.println("修改的用户名与数据库重复");
				e.printStackTrace();
			}
			String userId = user.getId();
			if(roleIds!=null){
				userService.update(userId,roleIds);
			}
		}else {
			String password = MD5Utils.md5(user.getPassword());
			user.setPassword(password);
			String userId = UUID.randomUUID().toString();
			user.setId(userId);
			userService.insert(user);
			if(roleIds!=null){
				userService.insert(userId,roleIds);
			}
			
		}
		//ra.addFlashAttribute("currentPage", currentPage);
		md.setViewName("redirect:/user/list");
		return md;
	}

}
