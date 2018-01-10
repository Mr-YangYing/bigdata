package cn.bytd.queryPage.utils;

import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.bytd.queryPage.TeacherQueryObject;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
/**
 * 
 * 说明:通用的查询工具类
 * @author yangying
 * @version 1.0
 * @date 2017-10-25 下午4:06:54
 *
 */
public class QueryUtil {

	public static PageResult query(IQueryObject qo,String tableName,RowMapper rm,JdbcTemplate jdbcTemplate) {
		//查询结果集,得到listData
		String baseSql = "select * from " + tableName + qo.getQuery(true)
				+ " limit ?,?";
		List<Object> newParames = new ArrayList<>(qo.getParameters());
/*		if(qo.getCurrentPage()==null){
			qo.setCurrentPage(1);
		}*/
		newParames.add((qo.getCurrentPage() - 1) * qo.getPageSize());
		newParames.add(qo.getPageSize());
		List listData = jdbcTemplate.query(baseSql, rm, newParames.toArray());
		System.out.println(baseSql);
		System.out.println(newParames);
		//查询结果总数,得到totalCount
		String countSql = "select count(*) from " + tableName
				+ qo.getQuery(false);
		int count = jdbcTemplate.queryForObject(countSql,Integer.class,qo.getParameters()
				.toArray());
		System.out.println(countSql);

		return new PageResult(listData, Integer.valueOf(count),
				qo.getCurrentPage(), qo.getPageSize());
	}
}
