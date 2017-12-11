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
 * 说明:封装Resource高级查询条件的查询对象
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午3:12:52
 *
 */

public class ResourceQueryObject extends QueryObject{
	private long taskId;//任务Id
	private String courseType;//课程类型
	
	/**
	 * 定义自身对象的查询条件和参数
	 */
	public void customizeQuery(){
		//时间范围查询的开始
		if (taskId!=0L) {
			super.addQuery("taskId = ?",taskId);
		}
		
		//增加排序功能(也可以先从前台输入,根据判断再动态添加)
		//super.setOrderBy("studentNumber",OrderBy.ASC);
		//super.setOrderBy("studentName",OrderBy.DESC);
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
}
