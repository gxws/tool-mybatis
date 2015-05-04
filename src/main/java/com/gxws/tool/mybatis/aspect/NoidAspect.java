package com.gxws.tool.mybatis.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 设置noid的值
 * 
 * @author zhuwl120820@gxwsxx.com 2015年2月5日下午3:13:01
 *
 */
public class NoidAspect {

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 在insert方法执行之前插入noid的值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param jp
	 *            JoinPoint对象
	 */
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
