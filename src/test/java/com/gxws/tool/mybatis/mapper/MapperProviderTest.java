package com.gxws.tool.mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gxws.tool.mybatis.test.mapper.TestTbMapper;
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
	@Before
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
	@After
	public void tearDown() throws Exception {
		session.close();
	}

	@Ignore
	@Test
	public void testInsert() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		TestTb tb = new TestTb();
		tb.setId("1234");
		tb.setName("name1234 insert");
		assertEquals(1, mapper.insert(tb));
	}

	@Ignore
	@Test
	public void testUpdate() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		TestTb tb = new TestTb();
		tb.setId("4028e5d24cb737b9014cb73d8ba80003");
		tb.setName("name1234 update");
		assertEquals(1, mapper.update(tb));
	}

	@Test
	public void testUpdateNotNull() {
		TestTbMapper mapper = session.getMapper(TestTbMapper.class);
		TestTb tb = new TestTb();
		tb.setId("4028e5d24cb737b9014cb73d8ba80003");
		tb.setName("name1234 update not null");
		tb.setStringName("stringName update not null");
		assertEquals(1, mapper.updateNotNull(tb));
	}

}
