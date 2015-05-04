tool-mybatis
============

mail list:朱伟亮 \<zhuwl120820@gxwsxx.com>

mybatis工具提供通用增删改查方法。<br>
主键id生成，noid生成。<br>

版本变更说明
---
# 1
## 1.0
### 1.0.3
完善了说明文档README.md。<br>
调整了项目依赖，top-config-project升级为1.0.3-SNAPSHOT。<br>
修改了获取noid的键名，旧的结构为"表名"，新的结构为"数据库名"."表名"。<br>
添加测试依赖，mariadb-java-client。<br>
添加测试依赖，slf4j-log4j12。<br>
添加测试依赖，apache-log4j-extras。<br>
添加了junit测试用例。<br>
修复了updateNotNull没有","分隔符的bug。

功能点
---
### 1、提供通用的、泛型的增删改查方法

	com.gxws.tool.mybaits.mapper.Mapper
	
继承自Mapper<T>接口的接口，不需要再实现以下方法

	public T select(String id);

	public String noid();

	public int insert(T o);

	public int insertNotNull(T o);

	public int update(T o);

	public int updateNotNull(T o);

	public int delete(String id);

具体方法的说明可以查看api或代码注释。

### 2、自动生成对象id和noid的值
由spring aop的方式，在执行insert*方法时，添加对象的id和noid的值。<br>
不使用配置而使用约定的方式进行操作。对所有泛型对象字段名为"id"和"noid"的字段，在执行insert开头的方法时，会自动填入相应的值。<br>
即如果字段名为"id"，则该字段对应为数据库主键，而且数值自动填入，不能指定。如果该字段不是对应数据库主键的话，则不能命名为"id"。<br>
如果字段命名为"noid"，则该字段拥有noid的属性和生成规则。如果字段不希望字段具有noid的属性和生成规则的话，则不能命名为"noid"。


依赖关系
---
org.springframework spring-aop 4.1<br>
org.mybatis mybatis 3.2.7<br>

使用方式
---
### 1、泛型增删改查
在配置文件classpath:mybatis.xml中添加plugin。

	<configuration>
		<plugins>
			<plugin interceptor="com.gxws.tool.mybaits.plugin.MapperPlugin"></plugin>
		</plugins>
	</configuration>

mapper接口继承com.gxws.tool.mybaits.mapper.Mapper。

	package com.gxws.service.demo.mapper;

	import com.gxws.service.demo.tb.DemoTb;
	import com.gxws.tool.mybaits.mapper.Mapper;
	
	public interface DemoTbMapper extends Mapper<DemoTb> {
	
	}
	
接口DemoTbMapper的对象，已经可以使用Mapper<DemoTb>提供的增删改查方法。

	DemoTb dtb = demoMapper.select(dm.getId());
	
### 2、使用自动生成id和noid的值
在配置文件spring-mybatis.xml中添加spring aop配置。

	<beans>
		<!-- mybatis mapper 层的aop配置 -->
		<aop:config>
			<aop:aspect id="id" ref="idAspect">
				<aop:pointcut id="insert"
					expression="execution(* com.gxws.service.demo.mapper.*.insert*(..))" />
				<aop:before method="before" pointcut-ref="insert" />
			</aop:aspect>
			<aop:aspect id="noid" ref="noidAspect">
				<aop:pointcut id="insert"
					expression="execution(* com.gxws.service.demo.mapper.*.insert*(..))" />
				<aop:before method="before" pointcut-ref="insert" />
			</aop:aspect>
		</aop:config>
		<!-- 生成数据库主键id值的aop bean -->
		<bean id="idAspect" class="com.gxws.tool.mybaits.aspect.IdAspect" />
		<!-- 生成各种编号noid值的aop -->
		<bean id="noidAspect" class="com.gxws.tool.mybaits.aspect.NoidAspect" />
	</beans>

在包com.gxws.service.demo.mapper以内所有类，方法名以insert开始的方法，在执行时会自动添加id和noid的值。
