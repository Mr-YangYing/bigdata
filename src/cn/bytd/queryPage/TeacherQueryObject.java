package cn.bytd.queryPage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;

import cn.bytd.queryPage.query.QueryObject;
import cn.bytd.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 说明:封装Teacher高级查询条件的查询对象
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午3:12:52
 *
 */

public class TeacherQueryObject extends QueryObject{
	private String teacherAccount;//教师账号
	private String teacherName;//教师姓名
	//private String keywords;//关键字查询
	
	/**
	 * 定义自身对象的查询条件和参数
	 */
	public void customizeQuery(){
		//教师的账号
		if (StringUtils.hasLength(teacherAccount)) {
			super.addQuery("teacherAccount like ?", "%" + teacherAccount + "%");
		}
		//教师的姓名
		if (StringUtils.hasLength(teacherName)) {
			super.addQuery("teacherName like ?", "%" + teacherName + "%");
		}
		//增加关键字查询
		/*if (StringUtils.hasLength(keywords)) {
			super.addQuery("keywords like ?", "%"+teacherAccount+"%","%"+teacherName+"%");
		}*/
		
		//增加排序功能(也可以先从前台输入,根据判断再动态添加)
		//super.setOrderBy("teacherAccount",OrderBy.ASC);
		//super.setOrderBy("teacherName",OrderBy.DESC);
	}

	public String getTeacherAccount() {
		return teacherAccount;
	}

	public void setTeacherAccount(String teacherAccount) {
		this.teacherAccount = teacherAccount;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
}
