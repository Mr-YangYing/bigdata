package cn.bytd.queryPage.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



import lombok.Getter;
import lombok.Setter;


public class QueryObject implements IQueryObject{
	
	//通过用户传入
	private Integer currentPage = 1;//当前页,默认为1
	private Integer pageSize = 7;//每页最多显示多少条数据

	//封装查询条件:account like ?
	private List<String> conditions = new ArrayList<>();
	//封装参数参数:%zhangsan%
	private List<Object> parameters = new ArrayList<>();
	//封装排序的列和对应的规则:account ASC 或者 username DESC;  LinkedHashMap保证添加顺序
	private Map<String, OrderBy> orderByMap = new LinkedHashMap<>();

	/**
	 * 获取拼接后的sql
	 */
	public String getQuery(boolean isOrderBy) {
		conditions.clear();
		parameters.clear();
		
		StringBuilder sql = new StringBuilder(200);
		this.customizeQuery();
		//-------------拼接条件-----------------------
		for (int i = 0; i < conditions.size(); i++) {
			if (i == 0) {
				sql.append(" WHERE ");//第一个条件
			} else {
				sql.append(" AND ");//不是第一个条件
			}
			sql.append(conditions.get(i));//拼接条件
		}
		
		//判断是否要增加排序
		if (isOrderBy) {
			//-------------拼接排序规则--------------------
			if (orderByMap.size()>0) {
				Set<Entry<String, OrderBy>> entrySet = orderByMap.entrySet();
				sql.append(" order by ");
				for (Entry<String, OrderBy> entry : entrySet) {
					String columnName = entry.getKey();
					OrderBy orderByType = entry.getValue();
					sql.append(columnName).append(" ").append(orderByType).append(",");
				}
				sql.deleteCharAt(sql.length()-1);//去除最后一个逗号;
			}
		}
		
		return sql.toString();
	}
	
	/**
	 * 返回查询的条件参数
	 */
	public List<Object> getParameters() {
		return this.parameters;
	}
	
	//专门暴露给子类,添加自身的查询方法
	protected void customizeQuery() {}
	//专门暴露给子类,让子类用于添加自身的查询条件和参数
	protected void addQuery(String condition,Object...param){
		this.conditions.add("("+condition+")");
		this.parameters.addAll(Arrays.asList(param));
	}
	
	//专门暴露给子类用于增加派逊功能的方法
	protected void setOrderBy(String columnName,OrderBy ascOrDesc){
		orderByMap.put(columnName,ascOrDesc);
	}
	
	//排序的规则
	enum OrderBy{
		ASC,DESC;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}

	public Map<String, OrderBy> getOrderByMap() {
		return orderByMap;
	}

	public void setOrderByMap(Map<String, OrderBy> orderByMap) {
		this.orderByMap = orderByMap;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	
}
