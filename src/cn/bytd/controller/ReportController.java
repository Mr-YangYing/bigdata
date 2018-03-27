package cn.bytd.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import cn.bytd.domain.Report;
import cn.bytd.domain.Resource;
import cn.bytd.service.IReportService;

/**
 * 
 * 说明:报告
 * @author yangying
 * @version 1.0
 * @date 2017年12月13日 下午4:20:04
 *
 *
 */
@Controller
@RequestMapping(value="/report")
public class ReportController {
	@Autowired
	private IReportService reportService;
	/**
	 * 上传报告
	 * @param uploadFile
	 * @param report
	 * @param session
	 * @param taskId
	 * @param courseId
	 * @param ra
	 * @return
	 */
	@RequestMapping(value="/reportUpload")
	public ModelAndView reportUpload(MultipartFile uploadFile,Report report,HttpSession session,long taskId,long courseId,String studentId,RedirectAttributes ra){
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
		String path = prop.getProperty("reportFilePath");
		
		File filepath = new File(path, filename);//路径拼接:上传文件路径+文件名
		   //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) { 
        	filepath.getParentFile().mkdirs();
        }
		report.setReportAddress(path + File.separator + filename);//设置文件的路径用于保存到数据库
		reportService.addReport(report,taskId,studentId);
		try {
			uploadFile.transferTo(new File(path + File.separator + filename));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		//方法之间传值
		ra.addAttribute("courseId", courseId);
		
		md.setViewName("redirect:/student/courseTaskDetail");
		return md;
	}
	/**
	 * 查看报告
	 * @param taskId
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/getReportByTaskId",method={RequestMethod.GET})
	public void getReportByTaskId(long taskId,String studentId,HttpServletResponse response) throws IOException{
		Report report = reportService.getReportByTaskId(taskId,studentId);
		//获取文件名
        String[] splits = report.getReportAddress().split("\\\\");
        String fileName = splits[splits.length-1];
        //获取下载文件路径
        File file = new File(report.getReportAddress());
        System.out.println(file);
        
        response.setContentType("application/pdf");  
        ServletOutputStream sos = response.getOutputStream();  
        FileInputStream in = new FileInputStream(report.getReportAddress());  
        byte data[] = new byte[1024];  
      
        int len = 0;  
        while ((len = in.read(data)) != -1) {  
            sos.write(data, 0, len);  
        }  
      
        sos.flush();  
        in.close();  
        sos.close();
        
	}
	/**
	 * 查询报告
	 * @param taskId
	 * @param studentId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/getReport",method={RequestMethod.GET})
	@ResponseBody
	public Report getReport(long taskId,String studentId) throws IOException{
		return reportService.getReportByTaskId(taskId,studentId);		
	}
}
