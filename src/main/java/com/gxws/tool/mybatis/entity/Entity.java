package com.gxws.tool.mybatis.entity;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 需要处理的泛型实体类信息
 * 
 * @author zhuwl120820@gxwsxx.com 2015年2月3日下午2:44:40
 *
 */
public class Entity {

	private Logger log = LoggerFactory.getLogger(getClass());

	// 实体类对象类名（包名+类名）
	private String entityClassName;

	// 实体类对象类
	private Class<?> entityClass;

	// 实体类对象字段集合（字段内"_"分隔，包含所有声明字段，不包含父类）
	private Set<String> entityFieldSet;

	// 实体类对象字段（字段内"_"分隔，字段间","分隔，包含所有声明字段，不包含父类）
	private String entityFieldString;

	// 数据库表名（下划线）
	private String dbTableName;

	// 数据库列名（字段内"_"分隔，字段间","分隔，包含所有声明字段，不包含父类）
	private String dbColumnNameString;

	// 数据库列名（字段内"_"分隔，包含所有声明字段，不包含父类）
	private Set<String> dbColumnNameSet;

	// mapper对象方法名（select，insert，update，delete）
	private String mapperMethodName;

	// mapper对象类名（包名+类名）
	private String mapperClassName;

	// mapper对象类
	private Class<?> mapperClass;

	// 是否调用通用方法
	private boolean subMapper;

	private final static String MAPPER_NAME = "com.gxws.tool.mybatis.mapper.Mapper";

	public Entity(String methodFullName) {
		this.setMapperMethodName(methodFullName);
		this.setMapperClassName(methodFullName);
		try {
			this.setMapperClass();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ParameterizedType pt = superMapper(this.mapperClass
				.getGenericInterfaces());
		if (null != pt) {
			this.setSubMapper(true);
			this.setEntityClass(pt);
			this.setEntityClassName();
			this.setDbTableName();
			this.setFieldAndColumn();
		}
	}

	private ParameterizedType superMapper(Type[] types) {
		Class<?> superClass = null;
		ParameterizedType pt = null;
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				pt = (ParameterizedType) type;
				superClass = (Class<?>) pt.getRawType();
				if (MAPPER_NAME.equals(superClass.getName())) {
					log.debug(this.getMapperClassName() + "是" + MAPPER_NAME
							+ "的子类");
					return pt;
				}
			}
			superClass = (Class<?>) type;
			pt = superMapper(superClass.getGenericInterfaces());
			if (null != pt) {
				return pt;
			}
		}
		log.debug(this.getMapperClassName() + "不是" + MAPPER_NAME + "的子类");
		return null;
	}

	public boolean isSubMapper() {
		return this.subMapper;
	}

	public String getDbTableName() {
		return dbTableName;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public String getMapperMethodName() {
		return mapperMethodName;
	}

	public String getMapperClassName() {
		return mapperClassName;
	}

	public Class<?> getMapperClass() {
		return mapperClass;
	}

	private void setDbTableName() {
		this.dbTableName = underline(this.getEntityClass().getSimpleName());
	}

	private void setEntityClass(ParameterizedType pt) {
		this.entityClass = (Class<?>) pt.getActualTypeArguments()[0];
	}

	private void setEntityClassName() {
		this.entityClassName = this.entityClass.getName();
	}

	private void setMapperMethodName(String mapperMethodName) {
		this.mapperMethodName = mapperMethodName.substring(mapperMethodName
				.lastIndexOf(".") + 1);
	}

	private void setMapperClassName(String mapperClassName) {
		this.mapperClassName = mapperClassName.substring(0,
				mapperClassName.lastIndexOf("."));
	}

	private void setMapperClass() throws ClassNotFoundException {
		this.mapperClass = Class.forName(this.mapperClassName, false, this
				.getClass().getClassLoader());
	}

	private void setSubMapper(boolean subMapper) {
		this.subMapper = subMapper;
	}

	public Set<String> getDbColumnNameSet() {
		return dbColumnNameSet;
	}

	private void setFieldAndColumn() {
		Field[] fields = this.entityClass.getDeclaredFields();
		this.entityFieldSet = new HashSet<>();
		this.dbColumnNameSet = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (Field field : fields) {
			this.entityFieldSet.add(field.getName());
			this.dbColumnNameSet.add(underline(field.getName()));
			sb.append(",");
			sb.append(field.getName());
		}
		this.entityFieldString = sb.substring(1);
		this.dbColumnNameString = underline(sb.substring(1));
	}

	public String getEntityFieldString() {
		return entityFieldString;
	}

	public static String underline(String uplow) {
		char[] uplowChar = uplow.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < uplowChar.length; i++) {
			if (Character.isUpperCase(uplowChar[i])) {
				sb.append("_");
				sb.append(Character.toLowerCase(uplowChar[i]));
			} else {
				sb.append(uplowChar[i]);
			}
		}
		if ("_".equals(sb.substring(0, 1))) {
			return sb.substring(1);
		} else {
			return sb.toString();
		}

	}

	public String getDbColumnNameString() {
		return dbColumnNameString;
	}

	public Set<String> getEntityFieldSet() {
		return entityFieldSet;
	}
}
