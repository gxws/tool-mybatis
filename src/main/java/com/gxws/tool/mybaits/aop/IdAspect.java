package com.gxws.tool.mybaits.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.gxws.tool.mybaits.entity.PkField;

/**
 * 设置主键的值
 * 
 * @author 朱伟亮
 * @create 2015年2月5日下午3:13:17
 *
 */
public class IdAspect {

	private Logger log = Logger.getLogger(getClass());

	private PkField pk = new PkField();

	public void before(JoinPoint jp) {
		Object entity = jp.getArgs()[0];
		String fieldName = pk.getEntityField();
		StringBuffer methodNameSetter = new StringBuffer("set");
		methodNameSetter.append(fieldName.substring(0, 1).toUpperCase());
		methodNameSetter.append(fieldName.substring(1));
		Method method = null;
		try {
			try {
				method = entity.getClass().getMethod(
						methodNameSetter.toString(), String.class);
			} catch (NoSuchMethodException e1) {
				log.debug("没有id属性");
				return;
			}
			method.invoke(entity, idValue());
		} catch (SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			log.error("主键值修改错误", e);
		}
	}

	private final String idValue() {
		String value = UUID.randomUUID().toString();
		StringBuffer sb = new StringBuffer(value.substring(0, 8));
		sb.append(value.substring(9, 13));
		sb.append(value.substring(14, 18));
		sb.append(value.substring(19, 23));
		sb.append(value.substring(24));
		value = sb.toString();
		return value;
	}
}
