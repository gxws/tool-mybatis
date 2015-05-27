package com.gxws.tool.mybatis.mapper;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.gxws.tool.mybatis.test.mapper.TestTbSubMapper;
import com.gxws.tool.mybatis.test.tb.TestTb;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class SubMapperProviderTest {

	private SqlSessionFactory sqlSessionFactory;

	private SqlSession session;

	/**
	 * @author zhuwl120820@gxwsxx.com
	 * @throws java.lang.Exception
	 */
	@BeforeSuite
	public void setUp() throws Exception {
		String resource = "mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();
	}

	/**
	 * @author zhuwl120820@gxwsxx.com
	 * @throws java.lang.Exception
	 */
	@AfterSuite
	public void tearDown() throws Exception {
		session.close();
	}

	@Test
	public void testInsert() {
		TestTbSubMapper mapper = session.getMapper(TestTbSubMapper.class);
		TestTb tb = new TestTb();
		tb.setId("1");
		tb.setName("name1 insert");
		Assert.assertEquals(1, mapper.insert(tb));
	}

	@Test
	public void testInsertNotNull() {
		TestTbSubMapper mapper = session.getMapper(TestTbSubMapper.class);
		TestTb tb = new TestTb();
		tb.setId("2");
		tb.setName("name2 insert not null");
		Assert.assertEquals(1, mapper.insertNotNull(tb));
	}

	@Test
	public void testSelect() {
		TestTbSubMapper mapper = session.getMapper(TestTbSubMapper.class);
		Assert.assertEquals("name3", mapper.select("3").getName());
	}

	@Test
	public void testUpdate() {
		TestTbSubMapper mapper = session.getMapper(TestTbSubMapper.class);
		TestTb tb = new TestTb();
		tb.setId("4");
		tb.setName("name4 update");
		tb.setTimestampName(new Date());
		tb.setDatetimeName(new Date());
		Assert.assertEquals(1, mapper.update(tb));
	}

	@Test
	public void testUpdateNotNull() {
		TestTbSubMapper mapper = session.getMapper(TestTbSubMapper.class);
		TestTb tb = new TestTb();
		tb.setId("5");
		tb.setName("name5 update not null");
		tb.setStringName("stringName update not null");
		tb.setTimestampName(new Date());
		tb.setDatetimeName(new Date());
		Assert.assertEquals(1, mapper.updateNotNull(tb));
	}

	@Test
	public void testDelete() {
		TestTbSubMapper mapper = session.getMapper(TestTbSubMapper.class);
		Assert.assertEquals(1, mapper.delete("6"));
	}
}
