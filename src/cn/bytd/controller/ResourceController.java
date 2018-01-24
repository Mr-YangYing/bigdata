package cn.bytd.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import cn.bytd.service.ITeacherService;

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
	@Autowired
	private ITeacherService teacherService;
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
	 * 根据任务Id获取资源
	 * @return
	 */
	@RequestMapping(value="/getResourceByTaskId")
	public ModelAndView getResourceByTaskId(long taskId){
		List<Resource> resourceList = resourceService.getResourceByTaskId(taskId);
		ModelAndView md = new ModelAndView();
		md.addObject("resourceList", resourceList);
		md.setViewName("views/student/resource");
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
		String filename = uploadFile.getOriginalFilename();//文件原始名称
		
		//读取配置文件,获取上传文件路径
		InputStream in = session.getServletContext().getResourceAsStream("/WEB-INF/classes/uploadFilePath.properties"); 
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String path = prop.getProperty("resourceFilePath");
		
		File filepath = new File(path, filename);//路径拼接:上传文件路径+文件名
		   //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) { 
        	filepath.getParentFile().mkdirs();
        }
		resource.setResourceAddr(path + File.separator + filename);//设置文件的路径用于保存到数据库
		//这里resource.getUploader()得到的是页面传入的教师的Id
		resource.setUploader(teacherService.getById(resource.getUploader()).getTeacherName());
		
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
/*	@RequestMapping(value="/resourceDownload",method={RequestMethod.GET})
    public ResponseEntity<byte[]> resourceDownload(HttpServletRequest request, long id) throws Exception{
    	Resource resource = resourceService.getById(id);
    	
       System.out.println(resource.getResourceAddr());
       String[] splits = resource.getResourceAddr().split("\\\\");
       String fileName = splits[splits.length-1];//文件名
       //下载文件路径
       String path = request.getServletContext().getRealPath("/images/");
       File file = new File(path + File.separator + fileName);
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
    }*/
    /**
     * 资源下载
     * @param id
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/resourceDownload",method={RequestMethod.GET})
	public void resourceDownload(long id, HttpServletResponse response) throws Exception {
    	Resource resource = resourceService.getById(id);
    	//获取文件名
        String[] splits = resource.getResourceAddr().split("\\\\");
        String fileName = splits[splits.length-1];
        //获取下载文件路径
        File file = new File(resource.getResourceAddr());
		// 设置响应头和客户端保存文件名
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		try {
			// 打开本地文件流
			InputStream inputStream = new FileInputStream(file);
			// 激活下载操作
			OutputStream os = response.getOutputStream();

			// 循环写入输出流
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
