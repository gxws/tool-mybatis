package com.gxws.tool.mybaits.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 朱伟亮
 * @create 2015年2月5日下午3:13:01
 *
 */
public class NoidAspect {

	private Logger log = LoggerFactory.getLogger(getClass());

	public void before(JoinPoint jp) {
		Object mapper = jp.getTarget();
		Object entity = jp.getArgs()[0];
		Method noidSetterMethod = null;
		try {
			try {
				noidSetterMethod = entity.getClass().getMethod("setNoid",
						String.class);
			} catch (NoSuchMethodException e1) {
				log.debug("没有noid属性");
				return;
			}
			Method noidGetterMethod = mapper.getClass().getMethod("noid",
					new Class<?>[0]);
			Object noidValue = noidGetterMethod.invoke(mapper, new Object[0]);
			noidSetterMethod.invoke(entity, noidValue);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.error("编号值修改错误", e);
		}
	}
}
