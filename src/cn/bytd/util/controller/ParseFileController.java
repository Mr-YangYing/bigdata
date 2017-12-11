package cn.bytd.util.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bytd.dao.ICourseDao;
import cn.bytd.domain.Course;
import cn.bytd.service.ICourseService;
import cn.bytd.util.DateUtil;
import cn.bytd.util.domain.FileInfo;


/**
 * 
 * 说明:解析Excel表格
 * @author yangying
 * @version 1.0
 * @date 2017-11-7 下午3:19:58
 *
 */
@Controller
@RequestMapping(value="/parseFile")
public class ParseFileController {
	
	/**
	 * 获取Excel表头
	 * @param fileInfo
	 * @param response
	 */
	@RequestMapping(value="/parseExcel")
	public void parseExcel(FileInfo fileInfo,HttpServletResponse response,HttpSession session){
		//String desPath = ServletActionContext.getServletContext().getRealPath("/uploads/"+DateUtil.getDate(date)+"/"+fileType);  
		//读取配置文件,获取上传文件路径
		InputStream in = session.getServletContext().getResourceAsStream("/WEB-INF/classes/uploadFilePath.properties"); 
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String path = prop.getProperty("batchFilePath");
		//
	    File destFile = new File(path, fileInfo.getUploadFileName());
	    System.out.println(destFile);

		try {
			//book = new HSSFWorkbook(new FileInputStream(destFile));
			InputStream is = new FileInputStream(destFile);
			HSSFWorkbook book = new HSSFWorkbook(is);
			// 得到第一张表
	        HSSFSheet sheet = book.getSheetAt(0);
	        HSSFRow row = sheet.getRow(0);
	        
	        String header = "";
            for (int j = 0; j < row.getLastCellNum(); j++) {  
                // 得到一行中的单元格  
                HSSFCell cell = row.getCell(j);
                header+=cell;
                if(j!=row.getLastCellNum()-1){
                	header+=" ";
                }
            }
            StringBuffer buffer = new StringBuffer();
    		buffer.append("{");
    		buffer.append("\"data\":\""+header+"\"");
    		buffer.append("}");
    		//responseWriter(buffer.toString());
    		
    		response.setContentType("application/json;charset=utf-8");
    		PrintWriter out = null;
    		try {
    			out = response.getWriter();
    			out.write(buffer.toString());
    		} catch (IOException e) {
    			e.printStackTrace();
    		}finally{
    			out.close();
    		}
    		
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 获取Excel表的数据内容
	 * @param fileInfo
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/parseExcelBody")
	@ResponseBody
	public List<List> parseExcelBody(FileInfo fileInfo,HttpServletResponse response,HttpSession session){
		List<List> bodyList = new ArrayList<>();//存放Excel所以数据信息
		//String desPath = fileInfo.getFilePath();
		//读取配置文件,获取上传文件路径
		InputStream in = session.getServletContext().getResourceAsStream("/WEB-INF/classes/uploadFilePath.properties"); 
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String path = prop.getProperty("batchFilePath");
		//
	    File destFile = new File(path, fileInfo.getUploadFileName());

	    try {
			InputStream is = new FileInputStream(destFile);
			HSSFWorkbook book = new HSSFWorkbook(is);
			HSSFSheet sheet = book.getSheetAt(0);
			//最后一行行号
			int totalRow = sheet.getLastRowNum();
			for (int i = 0; i <= totalRow; i++) {
				HSSFRow row = sheet.getRow(i);
				List<String> list = null;
				if (i!=0) {
					if (row!=null) {
						list = new ArrayList<>();
						for (Cell cell : row) {
							if (cell!=null) {
								String strCell = "";
								//判断每一个单元格中数据的类型,然后转换成String类型,添加到list集合中
						        switch (cell.getCellType()) {
						        case HSSFCell.CELL_TYPE_STRING:
						            strCell = cell.getStringCellValue();
						            list.add(strCell);
						            break;
						        case HSSFCell.CELL_TYPE_NUMERIC:
						            if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
						            	strCell = DateUtil.date2String(cell.getDateCellValue(), "yyyy-MM-dd");
						             }else {
						            	//这里cell.getNumericCellValue()获取的Excel中的值,默认是double类型的,有小数点,所以需要转为int,再转String
						            	 strCell = String.valueOf((int)cell.getNumericCellValue());
									}
						            list.add(strCell);
						            break;
						        case HSSFCell.CELL_TYPE_BOOLEAN:
						            strCell = String.valueOf(cell.getBooleanCellValue());
						            list.add(strCell);
						            break;
						        case HSSFCell.CELL_TYPE_BLANK:
						            strCell = "";
						            list.add(strCell);
						            break;
						        default:
						            strCell = "";
						            list.add(strCell);
						            break;
						        }
							}
						}
						bodyList.add(list);
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return bodyList;
	}
/*	public void parseCSV(){
		String desPath = model.getFilePath();
	    File destFile = new File(desPath, model.getUploadFileName());
	    FileReader fReader;
		try {
			fReader = new FileReader(destFile);
			CSVReader csvReader = new CSVReader(fReader);  
	        String[] strs = csvReader.readNext();
	        String header = StringUtils.join(strs, " ");
	        StringBuffer buffer = new StringBuffer();
    		buffer.append("{");
    		buffer.append("\"data\":\""+header+"\"");
    		buffer.append("}");
    		responseWriter(buffer.toString());
	        //csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}*/
}
