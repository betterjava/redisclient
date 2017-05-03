package com.cacheproxy.client.redisclient.support;

import java.io.Closeable;
import java.lang.reflect.Method;

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

//		boolean canInvoke = MethdoInvokeAuthUtil.canInvoke(method);
//		if (!canInvoke) {
//			throw new UnsupportedOperationException("this method can not be supported in model : "
//							+ JedisConfig.getInstance().getConfigType());
//		}
		

		Closeable closeAble = null;
		boolean isPipeline = false;
		try {
			isPipeline = MethdoInvokeAuthUtil.isPipeline(method);
			closeAble = JedisFactory.getJedis();
			System.out.println("获取。。。");
			return method.invoke(closeAble, args);
		} finally {
			if (closeAble != null && !isPipeline) {
				closeAble.close();
				System.out.println("释放连接。。。");
			}
		}
	}
}
