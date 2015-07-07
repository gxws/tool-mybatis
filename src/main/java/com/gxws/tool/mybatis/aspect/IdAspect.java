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

		StringBuffer methodNameGetter = new StringBuffer("get");
		methodNameGetter.append(fieldName.substring(0, 1).toUpperCase());
		methodNameGetter.append(fieldName.substring(1));

		StringBuffer methodNameSetter = new StringBuffer("s");
		methodNameSetter.append(methodNameGetter.substring(1));
		Method method = null;
		try {
			try {
				method = entity.getClass().getMethod(
						methodNameGetter.toString());
			} catch (NoSuchMethodException e1) {
				log.debug("没有" + methodNameGetter.toString() + "方法");
				return;
			}
			Object o = method.invoke(entity);
			if (null != o) {
				return;
			}
			try {
				method = entity.getClass().getMethod(
						methodNameSetter.toString(), String.class);
			} catch (NoSuchMethodException e) {
				log.debug("没有" + methodNameSetter.toString() + "方法");
			}
			method.invoke(entity, Uuid.order());
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e2) {
			log.error("操作主键值错误", e2);
		}
	}
}
