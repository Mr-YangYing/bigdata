package cn.bytd.test;

import java.util.List;

import org.junit.Test;

import com.alibaba.druid.sql.PagerUtils;

import cn.bytd.dao.ITeacherDao;
import cn.bytd.dao.impl.TeacherDaoImpl;
import cn.bytd.domain.Teacher;

public class TeacherDaoTest {
	private ITeacherDao teacherDao = new TeacherDaoImpl();
	
	@Test
	public void testlist() throws Exception {
		List<Teacher> list = teacherDao.list();
		System.out.println(list);
		
	}
}
