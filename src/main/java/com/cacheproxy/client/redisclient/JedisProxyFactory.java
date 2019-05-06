package com.cacheproxy.client.redisclient;

import com.cacheproxy.client.redisclient.support.AbstractJedisProxyFactory;

/**
 * @desc jedis代理  工厂类
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisProxyFactory extends AbstractJedisProxyFactory{

	public static JedisProxy createJedisProxy(){
		return initJedisProxy(DEFAULT_CONFIG_PROPERTIES);
	}
	
	public static JedisProxy createJedisProxy(String configProperties) {
		return initJedisProxy(configProperties);
	}
	
	public static ShardedJedisProxy createShardedJedisProxy(){
		return initShardedJedisProxy(DEFAULT_CONFIG_PROPERTIES);
	}
	
	public static ShardedJedisProxy createShardedJedisProxy(String configProperties){
		return initShardedJedisProxy(configProperties);
	}
	
}
