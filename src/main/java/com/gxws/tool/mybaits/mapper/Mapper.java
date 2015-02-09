package com.gxws.tool.mybaits.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * 提供基础增删改查的泛型调用方法
 * 
 * @author 朱伟亮
 * @create 2015年2月1日下午4:26:55
 *
 */
public interface Mapper<T> {

	@SelectProvider(method = "fakeSQL", type = MapperProvider.class)
	public T select(String id);

	@SelectProvider(method = "fakeSQL", type = MapperProvider.class)
	public String noid();

	@InsertProvider(method = "fakeSQL", type = MapperProvider.class)
	public int insert(T o);

	@InsertProvider(method = "fakeSQL", type = MapperProvider.class)
	public int insertNotNull(T o);

	@UpdateProvider(method = "fakeSQL", type = MapperProvider.class)
	public int update(T o);

	@UpdateProvider(method = "fakeSQL", type = MapperProvider.class)
	public int updateNotNull(T o);

	@DeleteProvider(method = "fakeSQL", type = MapperProvider.class)
	public int delete(String id);
}
