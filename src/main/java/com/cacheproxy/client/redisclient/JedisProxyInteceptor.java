package com.cacheproxy.client.redisclient;

import java.io.Closeable;
import java.lang.reflect.Method;

import com.cacheproxy.client.redisclient.config.JedisConfig;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @desc 代理类管理器
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisProxyInteceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		/**
		 * 1.先判断 jedis 是那种类型 2.然后判断改方法 是否在改类型中
		 */
		boolean canInvoke = MethdoInvokeAuthUtil.canInvoke(method);
		if (!canInvoke) {
			throw new UnsupportedOperationException("this method can not be supported in model : "+JedisConfig.getInstance().getConfigType());
		}

		Closeable closeAble = null;
		try {
			closeAble = (Closeable) JedisPoolFactory.getJedisPool().getResource();
			return method.invoke(closeAble, args);
		} finally {
			if (closeAble != null) {
				closeAble.close();
			}
		}
	}
}
