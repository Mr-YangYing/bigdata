package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.ILaboratoryDao;
import cn.bytd.domain.Laboratory;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;

/**
 * 
 * 说明:实验室Dao
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:22:04
 *
 */
@Repository("laboratoryDao")
public class LaboratoryDaoImpl implements ILaboratoryDao {

	private RowMapper<Laboratory> rm = new RowMapperLaboratory();//Laboratory通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 查询所有
	 */
	public List<Laboratory> list() {
		return jdbcTemplate.query("select * from laboratory", rm, new Object[] {});
	}


	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "laboratory", rm,jdbcTemplate);
	}


	/**
	 * 分页查询
	 */
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		//查询结果集,得到listData
		String baseSql = "select * from laboratory limit ?,?";
		List<Laboratory> listData = jdbcTemplate.query(baseSql, rm, (currentPage - 1) * pageSize, pageSize);
		//查询结果总数,得到totalCount
		String countSql = "select count(*) from laboratory";
		int count = jdbcTemplate.queryForObject(countSql, Integer.class);
		
		return new PageResult(listData, Integer.valueOf(count), currentPage, pageSize);
	}


	/**
	 * 根据id删除
	 */
	public void delete(long id) {
		//解除course关联表
		jdbcTemplate.update("delete from laboratory where id = ?", id);
	}



	/**
	 * 批量删除
	 */
	public void batchDelete(Long[] ids) {
		final List<Long> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		//解除course关联表
		jdbcTemplate.batchUpdate("update course set laboratoryId = null where laboratoryId = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
		//删除实验室
		jdbcTemplate.batchUpdate("delete from laboratory where id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
	}


	/**
	 * 根据id获取
	 */
	public Laboratory getById(long id) {
		Laboratory laboratory = null;
		//避免出现org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
		try {
			laboratory = jdbcTemplate.queryForObject("select * from laboratory where id = ?",rm,id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return laboratory;
	}
	

	/**
	 * 修改
	 */
	public void update(Laboratory Laboratory) {
		jdbcTemplate.update("update Laboratory set labAddress = ? where id = ?",Laboratory.getLabAddress(),Laboratory.getId());
	}


	/**
	 * 添加
	 */
	public void insert(Laboratory Laboratory) {
		jdbcTemplate.update("insert into laboratory(labAddress)values(?)",Laboratory.getLabAddress());
	}

	/**
	 * 获取列名
	 */
	public List<String> getColumnName() {
		List<String> list = new ArrayList<>();
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from laboratory");
		SqlRowSetMetaData metaData = sqlRowSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 2; i <=columnCount; i++) {
			list.add(metaData.getColumnName(i));
		}
		return list;
	}



	/**
	 * 批量增加
	 */
	public void batchUpdate(List<Laboratory> list) {
		
		final List<Laboratory> tempList = list;
		
		String sql = "insert into Laboratory(labAddress)values(?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, tempList.get(i).getLabAddress());
			}
			
			@Override
			public int getBatchSize() {
				return tempList.size();
			}
		});
	}



	
	/**
	 * 
	 * 内部类:Laboratory类结果集处理器
	 *
	 */
	private class RowMapperLaboratory implements RowMapper<Laboratory> {

		@Override
		public Laboratory mapRow(ResultSet rs, int rowNum) throws SQLException {
			Laboratory Laboratory = new Laboratory();
			Laboratory.setId(rs.getLong("id"));
			Laboratory.setLabAddress(rs.getString("labAddress"));
			return Laboratory;
		}

	}


}
