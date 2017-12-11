package cn.bytd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.IResourceDao;
import cn.bytd.domain.Resource;
import cn.bytd.domain.Student;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;
/**
 * 
 * 说明:资源Dao实现类
 * @author yangying
 * @version 1.0
 * @date 2017-11-23 上午11:20:50
 *
 */
@Repository("resourceDao")
public class ResourceDaoImpl implements IResourceDao{
	
	private RowMapper<Resource> rm = new RowMapperResource();//Student通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 高级查询+分页查询
	 * @return
	 */
	public PageResult query(IQueryObject qo){
		return QueryUtil.query(qo, "resource", rm, jdbcTemplate);
	}
	
	/**
	 * 根据资源Id删除资源
	 * @param resourceId
	 */
	public void resourceDelete(long resourceId){
		jdbcTemplate.update("delete from resource where id = ?", resourceId);
	}
	
	public Resource getById(long resourceId){
		return jdbcTemplate.queryForObject("select * from resource where id = ?",rm,resourceId);
	}
	
	/**
	 * 给指定任务添加资源
	 * @param taskId
	 */
	public void addResource(Resource resource,long taskId){
		jdbcTemplate.update("insert into resource(resourceNumber,resourceName,resourceAddr,uploader,uploadDate,description,taskId)" +
				"values(?,?,?,?,?,?,?)",resource.getResourceNumber(),resource.getResourceName(),resource.getResourceAddr(),resource.getUploader()
				,new Date(),resource.getDescription(),taskId);
	};
	
	/**
	 * 更新资源
	 */
	public void updateResource(Resource resource) {
		jdbcTemplate.update("update resource set resourceNumber=?,resourceName=?,resourceAddr=?,uploadDate=?,description=? where id=?",
				resource.getResourceNumber(),resource.getResourceName(),resource.getResourceAddr(),new Date(),resource.getDescription(),resource.getId());
	}
	
	
	/**
	 * 
	 * 说明:资源结果集处理类
	 * @author yangying
	 * @version 1.0
	 * @date 2017-11-23 上午11:29:55
	 *
	 */
    private	class RowMapperResource implements RowMapper<Resource>{

		@Override
		public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
			Resource resource = new Resource();
			resource.setId(rs.getLong("id"));
			resource.setResourceNumber(rs.getString("resourceNumber"));
			resource.setResourceName(rs.getString("resourceName"));
			resource.setResourceAddr(rs.getString("resourceAddr"));
			resource.setUploader(rs.getString("uploader"));
			resource.setUploadDate(rs.getDate("uploadDate"));
			resource.setDescription(rs.getString("description"));
			return resource;
		}
		
	}

}
