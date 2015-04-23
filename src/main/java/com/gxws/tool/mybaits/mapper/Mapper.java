package com.gxws.tool.mybaits.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * 提供基础增删改查的泛型调用方法
 * 
 * @author zhuwl120820@gxwsxx.com 2015年2月1日下午4:26:55
 *
 */
public interface Mapper<T> {

	/**
	 * select语句，根据id查询数据
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param id
	 *            数据的主键
	 * @return 查询数据的对象，没有则返回null
	 */
	@SelectProvider(method = "fakeSQL", type = MapperProvider.class)
	public T select(String id);

	/**
	 * 获取对应表的noid值，由insert或insert*方法自动调用，不需要显式调用
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return noid的值
	 */
	@SelectProvider(method = "fakeSQL", type = MapperProvider.class)
	public String noid();

	/**
	 * insert语句，将对象“所有”的字段加入sql语句中，“包括”值为null的字段
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param o
	 *            要insert的对象
	 * @return 操作的记录数
	 */
	@InsertProvider(method = "fakeSQL", type = MapperProvider.class)
	public int insert(T o);

	/**
	 * insert语句，将对象中“有值”的字段动态加入sql语句中，“不包括”值为null的字段
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param o
	 *            要insert的对象
	 * @return 操作的记录数
	 */
	@InsertProvider(method = "fakeSQL", type = MapperProvider.class)
	public int insertNotNull(T o);

	/**
	 * update语句，将对象“所有”的字段加入sql语句中，“包括”值为null的字段
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param o
	 *            要update的对象
	 * @return 操作的记录数
	 */
	@UpdateProvider(method = "fakeSQL", type = MapperProvider.class)
	public int update(T o);

	/**
	 * update语句，将对象中“有值”的字段动态加入sql语句中，“不包括”值为null的字段
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param o
	 *            要update的对象
	 * @return 操作的记录数
	 */
	@UpdateProvider(method = "fakeSQL", type = MapperProvider.class)
	public int updateNotNull(T o);

	/**
	 * delete语句，根据id查询数据
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param id
	 *            数据的主键
	 * @return 查询数据的对象，没有则返回null
	 */
	@DeleteProvider(method = "fakeSQL", type = MapperProvider.class)
	public int delete(String id);
}
