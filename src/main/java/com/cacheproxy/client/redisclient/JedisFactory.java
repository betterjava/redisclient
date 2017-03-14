package com.cacheproxy.client.redisclient;

import net.sf.cglib.proxy.Enhancer;

/**
 * @desc jedis 工厂类
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisFactory {

	private static JedisProxy jedis = initJedisProxy();

	/**
	 * 获取 jedisproxy
	 * 
	 * @return
	 */
	public static JedisProxy getJedisProxy() {
		return jedis;
	}

	private static JedisProxy initJedisProxy() {

		Enhancer enhancer = new Enhancer();
		// 设置需要创建子类的类
		enhancer.setSuperclass(JedisProxy.class);
		enhancer.setCallback(new JedisProxyInteceptor());
		// 通过字节码技术动态创建子类实例
		return (JedisProxy) enhancer.create();
	}
}
