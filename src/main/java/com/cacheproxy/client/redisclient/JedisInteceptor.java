package com.cacheproxy.client.redisclient;

import java.io.Closeable;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * @desc 代理类管理器
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisInteceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		
		// TODO 这里是不是还有没有考虑到的地方
		Closeable closeAble = null;
		try {
			closeAble =  (Closeable) JedisPoolFactory.getJedisPool().getResource();
			return method.invoke(closeAble, args);
		} finally {
			if (closeAble != null) {
				closeAble.close();
			}
		}
	}
}
