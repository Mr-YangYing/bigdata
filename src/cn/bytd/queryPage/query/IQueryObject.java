package cn.bytd.queryPage.query;

import java.util.List;

/**
 * 
 * 说明:查询对象接口规范
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:24:52
 *
 */
public interface IQueryObject {
	/**
	 * 返回拼接的查询条件
	 * @param isOrderBy
	 * @return
	 */
	String getQuery(boolean isOrderBy);
	/**
	 * 返回getQuery方法拼接查询sql中占位符对应的参数
	 * @return
	 */
	List<Object> getParameters();
	/**
	 * 获取当前页
	 * @return
	 */
	Integer getCurrentPage();
	/**
	 * 获取每天显示条数
	 * @return
	 */
	Integer getPageSize();
}
