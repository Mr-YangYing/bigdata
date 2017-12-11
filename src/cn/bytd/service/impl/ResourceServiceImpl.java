package cn.bytd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bytd.dao.IResourceDao;
import cn.bytd.domain.Resource;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.service.IResourceService;

/**
 * 
 * 说明:资源接口实现
 * @author yangying
 * @version 1.0
 * @date 2017-11-23 上午11:18:51
 *
 */
@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements IResourceService {
	@Autowired
	private IResourceDao resourceDao;

	/**
	 * 高级查询+分页查询
	 * @return
	 */
	public PageResult query(IQueryObject qo) {
		return resourceDao.query(qo);
	};

	/**
	 * 根据资源Id删除资源
	 * @param resourceId
	 */
	public void resourceDelete(long resourceId) {
		resourceDao.resourceDelete(resourceId);
	};

	/**
	 * 给指定任务添加资源
	 * @param taskId
	 */
	public void addResource(Resource resource, long taskId) {
		resourceDao.addResource(resource, taskId);
	};

	/**
	 * 更新资源
	 * @param resource
	 */
	public void updateResource(Resource resource) {
		resourceDao.updateResource(resource);
	};
	
	/**
	 * 根据ID获取资源
	 * @param resourceId
	 * @return
	 */
	public Resource getById(long resourceId){
		return resourceDao.getById(resourceId);
	};
}
