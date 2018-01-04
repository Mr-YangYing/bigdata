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


import cn.bytd.domain.Permission;
import cn.bytd.queryPage.PermissionQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.IPermissionService;

/**
 * 
 * 说明:权限Controller
 * @author yangying
 * @version 1.0
 * @date 2017年12月26日 上午10:59:12
 *
 *
 */
@Controller
@RequestMapping(value="/permission")
public class PermissionController {
	
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,PermissionQueryObject qo){
		//可以获取另一个方法传入的值
/*		
		Map<String, Object> modelMap = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		 if (modelMap!=null) {
			 int currentPage = (int) modelMap.get("currentPage");
			 qo.setCurrentPage(currentPage);
		}*/
		PageResult pageResult = permissionService.query(qo);
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/admin/permission");
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
		System.out.println(id);
		permissionService.delete(id);
		ModelAndView md = new ModelAndView();
		md.setViewName("redirect:/permission/list");
		return md;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/batchDelete",method={RequestMethod.GET})
	public ModelAndView batchDelete(String[] ids){
		permissionService.batchDelete(ids);
		return null;
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET})
	@ResponseBody
	public Permission get(String id){
		return permissionService.getById(id);
	}
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getPermissionByRoleId",method={RequestMethod.GET})
	@ResponseBody
	public List<Permission> getPermissionByRoleId(String id){
		return permissionService.getPermissionByRoleId(id);
	}
	
	/**
	 * 修改或添加
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(Permission permission,String pid/*,int currentPage,RedirectAttributes ra*/){
		ModelAndView md = new ModelAndView();
		if (!permission.getId().equals("-1")) {
			permissionService.update(permission);
		}else {
			//设置Id
			String uuid = UUID.randomUUID().toString();
			permission.setId(uuid);
			//设置父权限
			Permission parentPermission = permissionService.getById(pid);
			if(parentPermission==null){
				permission.setParentPermission(null);
			}else{
				permission.setParentPermission(parentPermission);
			}
			permissionService.insert(permission);
		}
		//ra.addFlashAttribute("currentPage", currentPage);
		md.setViewName("redirect:/permission/list");
		return md;
	}
	/**
	 * 权限列表
	 * @return
	 */
	@RequestMapping(value="/permissionList")
	@ResponseBody
	public List<Permission> permissionList(){
		List<Permission> permissionList = permissionService.getPermissionList();
		return permissionList;
	}
	/**
	 * 权限树
	 * @return
	 */
	@RequestMapping(value="/permissionListZtree")
	@ResponseBody
	public List<Permission> permissionListZtree(){
		List<Permission> permissionList = permissionService.getPermissionListZtree();
		return permissionList;
	}
}
