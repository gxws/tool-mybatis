package com.gxws.tool.mybatis.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxws.tool.common.uuid.Uuid;
import com.gxws.tool.mybatis.entity.PkField;

/**
 * 设置主键的值
 * 
 * @author zhuwl120820@gxwsxx.com 2015年2月5日下午3:13:17
 *
 */
public class IdAspect {

	private Logger log = LoggerFactory.getLogger(getClass());

	private PkField pk = new PkField();

	/**
	 * 在insert方法执行之前插入id的值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param jp
	 *            JoinPoint对象
	 */
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
			method.invoke(entity, Uuid.order());
		} catch (SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			log.error("主键值修改错误", e);
		}
	}
}
