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
 * 说明:封装User高级查询条件的查询对象
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午3:12:52
 *
 */

public class UserQueryObject extends QueryObject{
	private String username;//用户名
	//private String keywords;//关键字查询
	
	/**
	 * 定义自身对象的查询条件和参数
	 */
	public void customizeQuery(){
		//学生的学号
		if (StringUtils.hasLength(username)) {
			super.addQuery("username like ?", "%" + username + "%");
		}
		//增加关键字查询
		/*if (StringUtils.hasLength(keywords)) {
			super.addQuery("keywords like ?", "%"+studentNumber+"%","%"+studentName+"%");
		}*/
		
		//增加排序功能(也可以先从前台输入,根据判断再动态添加)
		//super.setOrderBy("studentNumber",OrderBy.ASC);
		//super.setOrderBy("studentName",OrderBy.DESC);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
}
