package cn.bytd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.ILaboratoryDao;
import cn.bytd.domain.Laboratory;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.ILaboratoryService;
/**
 * 
 * 说明:实验室业务类
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:21:32
 *
 */
@Service("laboratoryService")
@Transactional
public class LaboratoryServiceImpl implements ILaboratoryService{
	@Autowired
	private ILaboratoryDao laboratoryDao;
	
	public List<Laboratory> list(){
		return laboratoryDao.list();
	}
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		return laboratoryDao.queryPage(currentPage, pageSize);
	}
	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return laboratoryDao.query(qo);
	}
	/**
	 * 根据ID删除
	 */
	public void delete(long id) {
		laboratoryDao.delete(id);
	}
	/**
	 * 批量删除
	 */
	public void batchDelete(Long[] ids){
		laboratoryDao.batchDelete(ids);
	}
	/**
	 * 根据id获取
	 */
	public Laboratory getById(long id) {
		return laboratoryDao.getById(id);
	}
	/**
	 * 修改
	 */
	public void update(Laboratory Laboratory) {
		laboratoryDao.update(Laboratory);
	}
	/**
	 * 添加
	 */
	public void insert(Laboratory Laboratory) {
		laboratoryDao.insert(Laboratory);
	}
	/**
	 * 获取列名
	 */
	public List<String> getColumnName(){
		return laboratoryDao.getColumnName();
	}
	public void batchUpdate(List<Laboratory> list) {
		laboratoryDao.batchUpdate(list);
	}
}
