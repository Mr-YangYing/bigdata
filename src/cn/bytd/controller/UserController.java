package cn.bytd.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.internal.bind.v2.TODO;

import cn.bytd.domain.Role;
import cn.bytd.domain.User;
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
				//TODO studentId后面需要修改
				md.setViewName("redirect:/student/courseList?studentId=1");
			}
			if("admin".equals(permissionName)){
				//TODO studentId后面需要修改
				md.setViewName("redirect:/student/courseList?studentId=1");
			}
		}
		
		return md;
	}
}
