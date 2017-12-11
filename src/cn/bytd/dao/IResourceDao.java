package cn.bytd.dao;

import java.util.List;

import cn.bytd.domain.Resource;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;

/**
 * 
 * 说明:资源Dao接口
 * @author yangying
 * @version 1.0
 * @date 2017-11-23 上午11:19:45
 *
 */
public interface IResourceDao {
	/**
	 * 高级查询+分页查询
	 * @return
	 */
	PageResult query(IQueryObject qo);

	/**
	 * 根据资源Id删除资源
	 * @param resourceId
	 */
	void resourceDelete(long resourceId);

	/**
	 * 给指定任务添加资源
	 * @param taskId
	 */
	void addResource(Resource resource, long taskId);

	/**
	 * 更新资源
	 * @param resource
	 */
	void updateResource(Resource resource);

	/**
	 * 根据ID获取资源
	 * @param resourceId
	 * @return
	 */
	Resource getById(long resourceId);

}
