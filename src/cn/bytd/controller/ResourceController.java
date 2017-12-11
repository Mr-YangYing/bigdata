package cn.bytd.controller;



import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import cn.bytd.domain.Resource;
import cn.bytd.queryPage.ResourceQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.service.ICourseService;
import cn.bytd.service.IResourceService;

/**
 * 
 * 说明:资源controller
 * @author yangying
 * @version 1.0
 * @date 2017-11-22 下午4:37:03
 *
 */
@RequestMapping(value="/resource")
@Controller
public class ResourceController {
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private ICourseService courseService;
	/**
	 * 资源列表
	 * @param qo
	 * @param courseType
	 * @return
	 */
	@RequestMapping(value="/resourceList")
	public ModelAndView resourceList(ResourceQueryObject qo,HttpServletRequest request){
		//可以获取另一个方法传入的值
		Map<String, Object> modelMap = (Map<String, Object>) RequestContextUtils.getInputFlashMap(request);
		if (modelMap != null) {
			long taskId = (long) modelMap.get("taskId");
			String courseType = (String) modelMap.get("courseType");
			qo.setTaskId(taskId);
			qo.setCourseType(courseType);
		}
		
		PageResult pageResult = resourceService.query(qo);
		ModelAndView md = new ModelAndView();
		md.addObject("qo", qo);
		md.addObject("pageResult", pageResult);
		md.setViewName("views/teacher/resource");
		return md;
	}
	/**
	 * 资源删除
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value="/resourceDelete",method={RequestMethod.GET})
	public ModelAndView resourceDelete(long resourceId){
		ModelAndView md = new ModelAndView();
		resourceService.resourceDelete(resourceId);
		return null;
	}
	
	@RequestMapping(value="/getById",method={RequestMethod.GET})
	@ResponseBody
	public Resource getById(long id){
		Resource resource = resourceService.getById(id);
		return resource;
	}
	
	/**
	 * 资源上传/修改
	 * @return
	 */
	@RequestMapping(value="/resourceUpload")
	public ModelAndView resourceUpload(MultipartFile uploadFile,Resource resource,HttpSession session,long taskId,String courseType,RedirectAttributes ra){
		ModelAndView md = new ModelAndView();
		String path = session.getServletContext().getRealPath("/images/");//上传文件路径
		String filename = uploadFile.getOriginalFilename();//文件原始名称
		File filepath = new File(path, filename);//路径拼接:上传文件路径+文件名
		   //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) { 
        	filepath.getParentFile().mkdirs();
        }
		resource.setResourceAddr(path + File.separator + filename);//设置文件的路径用于保存到数据库
		if(resource.getId()!=-1){
			resourceService.updateResource(resource);
		}else {
			resourceService.addResource(resource,taskId);
		}
		
		try {
			uploadFile.transferTo(new File(path + File.separator + filename));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		//方法之间传值
		ra.addAttribute("taskId", taskId);
		ra.addAttribute("courseType", courseType);
		
		md.setViewName("redirect:/resource/resourceList");
		return md;
	}
	/**
	 * 资源下载
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resourceDownload",method={RequestMethod.GET})
    public ResponseEntity<byte[]> resourceDownload(HttpServletRequest request, long id) throws Exception{
    	Resource resource = resourceService.getById(id);
    	
       System.out.println(resource.getResourceAddr());
       String[] splits = resource.getResourceAddr().split("\\\\");
       String fileName = splits[splits.length-1];//文件名
       //下载文件路径
/*       String path = request.getServletContext().getRealPath("/images/");
       File file = new File(path + File.separator + fileName);*/
       File file = new File(resource.getResourceAddr());
       HttpHeaders headers = new HttpHeaders();  
       //下载显示的文件名，解决中文名称乱码问题  
       String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
       System.out.println(fileName);
       //通知浏览器以attachment（下载方式）打开图片
       headers.setContentDispositionFormData("attachment", downloadFielName); 
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);  
    }
}
