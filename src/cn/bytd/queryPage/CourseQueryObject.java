package cn.bytd.queryPage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.format.annotation.DateTimeFormat;

import cn.bytd.domain.Teacher;
import cn.bytd.queryPage.query.QueryObject;
import cn.bytd.util.DateUtil;
import cn.bytd.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 说明:封装Course高级查询条件的查询对象
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午3:12:52
 *
 */

public class CourseQueryObject extends QueryObject{
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String courseName;//课程名称
	private String keywords;//关键字查询
	
	/**
	 * 定义自身对象的查询条件和参数
	 */
	public void customizeQuery(){
		//时间范围查询的开始
		if (startTime!=null) {
			super.addQuery("startDate >= ?",DateUtil.getStartTime(startTime));
		}
		//时间范围查询的结束
		if (endTime!=null) {
			super.addQuery("startDate <= ?",DateUtil.getEndTime(endTime));
		}
		//课程的名称
		if (StringUtils.hasLength(courseName)) {
			super.addQuery("courseName like ?", "%" + courseName + "%");
		}
		//增加关键字查询
		if (StringUtils.hasLength(keywords)) {
			super.addQuery("courseName like ?","%"+keywords+"%");
		}
		
		//增加排序功能(也可以先从前台输入,根据判断再动态添加)
		//super.setOrderBy("studentNumber",OrderBy.ASC);
		//super.setOrderBy("studentName",OrderBy.DESC);
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
}
