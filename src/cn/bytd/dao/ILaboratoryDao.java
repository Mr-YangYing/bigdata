package cn.bytd.dao;

import java.util.List;

import cn.bytd.domain.Laboratory;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
/**
 * 
 * 说明:实验室Dao接口
 * @author yangying
 * @version 1.0
 * @date 2017年12月12日 上午10:18:13
 *
 *
 */
public interface ILaboratoryDao {
	/**
	 * 普通查询
	 * @return
	 */
	List<Laboratory> list();

	/**
	 * 分页查询
	 * @param currentPage 当前第几页
	 * @param pageSize 每页最多显示多少条数据
	 * @return 封装好结果集和分页信息的所有数据
	 */
	PageResult queryPage(Integer currentPage, Integer pageSize);
	
	/**
	 * 高级查询+分页查询
	 * @param qo 封装了所有的包含条件的查询信息和翻页的信息(currentPage, pageSize)
	 * @return 封装好的包含高级查询的结果集和分页信息的所有数据
	 */
	PageResult query(IQueryObject qo);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	void delete(long id);
	/**
	 * 批量删除
	 * @param ids
	 */
	void batchDelete(Long[] ids);
	/**
	 * 根据id获取
	 * @param id
	 */
	Laboratory getById(long id);
	/**
	 * 修改
	 * @param laboratory
	 */
	void update(Laboratory laboratory);
	/**
	 * 添加
	 * @param laboratory
	 */
	void insert(Laboratory laboratory);
	/**
	 * 获取所有列名
	 * @return
	 */
	List<String> getColumnName();
	
	/**
	 * 批量增加
	 */
	void batchUpdate(List<Laboratory> list);
}
