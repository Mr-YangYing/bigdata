package cn.bytd.util.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * 说明:文件上传控制器
 * @author yangying
 * @version 1.0
 * @date 2017-11-7 下午3:12:05
 *
 */
@Controller
@RequestMapping(value="/uploadFile")
public class FileUploadController {

	@RequestMapping(value="/upload")
	public void upload(MultipartFile upload,HttpSession session){
		//原始文件名称
		String originalFilename = upload.getOriginalFilename();
		//新文件名称
		String newFileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
		//写文件
		try {
			//读取配置文件,获取上传文件路径
			InputStream in = session.getServletContext().getResourceAsStream("/WEB-INF/classes/uploadFilePath.properties"); 
			Properties prop = new Properties();
			try {
				prop.load(in);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String path = prop.getProperty("batchFilePath");
			
			File filepath = new File(path, originalFilename);//路径拼接:上传文件路径+文件名
			   //判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	        	filepath.getParentFile().mkdirs();
	        }
			System.out.println(path);
			upload.transferTo(new File(path+File.separator+originalFilename));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 跳转到Excel导入界面
	 * @return
	 */
	@RequestMapping(value="/loadImportExcel",method={RequestMethod.GET})
	 	public ModelAndView loadImport(String domainName){
		 ModelAndView md = new ModelAndView();
		 md.addObject("domainName", domainName);
		 md.setViewName("views/common/import_excel/import_excel");
		 return md;
	 }
}
