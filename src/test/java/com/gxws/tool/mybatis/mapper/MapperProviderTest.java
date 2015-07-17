package com.gxws.tool.mybatis.mapper;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.gxws.tool.mybatis.test.mapper.TestTbMapper;
import com.gxws.tool.mybatis.test.mapper.TestTbSubMapper;
import com.gxws.tool.mybatis.test.tb.TestTb;

/**
 * @author zhuwl120820@gxwsxx.com
 *
 */
public class MapperProviderTest {

	private SqlSessionFactory sqlSessionFactory;

	private SqlSession session;

	/**
	 * @author zhuwl120820@gxwsxx.com
	 * @throws java.lang.Exception
	 */
	@BeforeClass
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
	@AfterClass
	public void tearDown() throws Exception {
		session.close();
	}

	@Test(groups = { "supMapper" })
	public void testInsert() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		TestTb tb = new TestTb();
		tb.setId("1");
		tb.setName("name1 insert");
		Assert.assertEquals(mapper.insert(tb), 1);
	}

	@Test(groups = { "supMapper" })
	public void testInsertNotNull() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		TestTb tb = new TestTb();
		tb.setId("2");
		tb.setName("name2 insert not null");
		Assert.assertEquals(mapper.insertNotNull(tb), 1);
	}

	@Test(groups = { "supMapper" })
	public void testSelect() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		Assert.assertEquals(mapper.select("3").getName(), "name3");
	}

	@Test(groups = { "supMapper" })
	public void testUpdate() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		TestTb tb = new TestTb();
		tb.setId("4");
		tb.setName("name4 update");
		tb.setTimestampName(new Date());
		tb.setDatetimeName(new Date());
		Assert.assertEquals(mapper.update(tb), 1);
	}

	@Test(groups = { "supMapper" })
	public void testUpdateNotNull() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		TestTb tb = new TestTb();
		tb.setId("5");
		tb.setName("name5 update not null");
		tb.setStringName("stringName update not null");
		tb.setTimestampName(new Date());
		tb.setDatetimeName(new Date());
		Assert.assertEquals(mapper.updateNotNull(tb), 1);
	}

	@Test(groups = { "supMapper" })
	public void testDelete() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		Assert.assertEquals(mapper.delete("6"), 1);
	}

	@Test(groups = { "supMapper" })
	public void testCount() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		Assert.assertEquals(mapper.count(), 4);
	}
}
