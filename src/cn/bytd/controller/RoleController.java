package cn.bytd.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bytd.domain.Role;
import cn.bytd.queryPage.RoleQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.IRoleService;

/**
 * 
 * 说明:角色
 * @author yangying
 * @version 1.0
 * @date 2018年1月1日 下午2:11:33
 *
 *
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,RoleQueryObject qo){
		//可以获取另一个方法传入的值
/*		
		Map<String, Object> modelMap = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		 if (modelMap!=null) {
			 int currentPage = (int) modelMap.get("currentPage");
			 qo.setCurrentPage(currentPage);
		}*/
		PageResult pageResult = roleService.query(qo);
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/admin/role");
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
		roleService.delete(id);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/role/list");
		return md;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/batchDelete",method={RequestMethod.GET})
	public ModelAndView batchDelete(String[] ids){
		roleService.batchDelete(ids);
		return null;
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET})
	@ResponseBody
	public Role get(String id){
		return roleService.getById(id);
	}
	
	/**
	 * 修改或添加
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(Role role,String permissionIds/*,int currentPage,RedirectAttributes ra*/){
		ModelAndView md = new ModelAndView();
		if (!role.getId().equals("-1")) {
			roleService.update(role);
			String roleId = role.getId();
			roleService.update(roleId,permissionIds);
		}else {
			String roleId = UUID.randomUUID().toString();
			role.setId(roleId);
			roleService.insert(role);
			roleService.insert(roleId,permissionIds);
		}
		//ra.addFlashAttribute("currentPage", currentPage);
		md.setViewName("redirect:/role/list");
		return md;
	}
	/**
	 * 获取所有角色
	 * @return
	 */
	@RequestMapping(value="/roleList")
	@ResponseBody
	public List<Role> roleList(){
		List<Role> roleList = roleService.roleList();
		return roleList;
	}

}
