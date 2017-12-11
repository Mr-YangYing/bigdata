package cn.bytd.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import cn.bytd.dao.ITeacherDao;
import cn.bytd.domain.Course;
import cn.bytd.domain.Teacher;
import cn.bytd.queryPage.page.PageResult;
import cn.bytd.queryPage.query.IQueryObject;
import cn.bytd.queryPage.utils.QueryUtil;

/**
 * 
 * 说明:教师Dao
 * @author yangying
 * @version 1.0
 * @date 2017-10-23 下午7:22:04
 *
 */
@Repository("teacherDao")
public class TeacherDaoImpl implements ITeacherDao {

	private RowMapper<Teacher> rm = new RowMapperTeacher();//Teacher通用结果集处理器
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 查询所有
	 */
	public List<Teacher> list() {
		return jdbcTemplate.query("select * from teacher", rm, new Object[] {});
	}

	/**
	 * 高级查询
	 * @return
	 */
	/*public List<Teacher> query(TeacherQueryObject qo) {
		String sql = "select * from teacher" + qo.getQuery();
		return jdbcTemplate.query(sql, rm, qo.getParameters().toArray());
	}*/
	

	/**
	 * 高级查询+分页查询
	 */
	public PageResult query(IQueryObject qo) {
		return QueryUtil.query(qo, "teacher", rm,jdbcTemplate);
	}


	/**
	 * 分页查询
	 */
	@SuppressWarnings("deprecation")
	public PageResult queryPage(Integer currentPage, Integer pageSize) {
		//查询结果集,得到listData
		String baseSql = "select * from teacher limit ?,?";
		List<Teacher> listData = jdbcTemplate.query(baseSql, rm, (currentPage - 1) * pageSize, pageSize);
		//查询结果总数,得到totalCount
		String countSql = "select count(*) from teacher";
		int count = jdbcTemplate.queryForObject(countSql, Integer.class);
		return new PageResult(listData, Integer.valueOf(count), currentPage, pageSize);
	}


	/**
	 * 根据id删除
	 */
	public void delete(long id) {
		//删除关联表的教师
		jdbcTemplate.update("delete from course_teacher_config where tea_id = ?", id);
		jdbcTemplate.update("delete from teacher where id = ?", id);
	}

	/**
	 * 批量删除
	 */
	public void batchDelete(Long[] ids) {
		final List<Long> idList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		//删除关联表的教师
		jdbcTemplate.batchUpdate("delete from course_teacher_config where tea_id = ?",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1,idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
		//删除教师
		jdbcTemplate.batchUpdate("delete from teacher where id = ?",new BatchPreparedStatementSetter() {
			
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
	public Teacher getById(long id) {
		return jdbcTemplate.queryForObject("select * from teacher where id = ?",rm,id);
	}
	

	/**
	 * 修改
	 */
	public void update(Teacher teacher) {
		jdbcTemplate.update("update teacher set teacherAccount = ?,teacherName = ?,positionalTitles = ? where id = ?",
				teacher.getTeacherAccount(),teacher.getTeacherName(),teacher.getPositionalTitles(),teacher.getId());
	}
	
	/**
	 * 添加
	 */
	public void insert(Teacher teacher){
		jdbcTemplate.update("insert into teacher(teacherAccount,teacherName,positionalTitles)values(?,?,?)", teacher.getTeacherAccount(),
				teacher.getTeacherName(),teacher.getPositionalTitles());
	}
	
	
	/**
	 * 获取列名
	 */
	public List<String> getColumnName() {
		List<String> list = new ArrayList<>();
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from teacher");
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
	public void batchUpdate(List<Teacher> list) {
		
		final List<Teacher> tempList = list;
		
		String sql = "insert into teacher(teacherAccount,teacherName,positionalTitles)values(?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, tempList.get(i).getTeacherAccount());
				ps.setString(2, tempList.get(i).getTeacherName());
				ps.setString(3, tempList.get(i).getPositionalTitles());
			}
			
			@Override
			public int getBatchSize() {
				return tempList.size();
			}
		});
	}

	
	
	
	/**
	 * 
	 * 内部类:Teacher类结果集处理器
	 *
	 */
	private class RowMapperTeacher implements RowMapper<Teacher> {

		@Override
		public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
			Teacher teacher = new Teacher();
			teacher.setId(rs.getLong("id"));
			teacher.setTeacherAccount(rs.getString("teacherAccount"));
			teacher.setTeacherName(rs.getString("teacherName"));
			teacher.setPositionalTitles(rs.getString("positionalTitles"));
			return teacher;
		}

	}




}
