package com.cacheproxy.client.redisclient.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.proxy.Enhancer;

import com.cacheproxy.client.redisclient.JedisProxy;
import com.cacheproxy.client.redisclient.ShardedJedisProxy;

/**
 * @author cookie
 * @date May 3, 2017
 */
public class AbstractJedisProxyFactory {

	protected final static String DEFAULT_CONFIG_PROPERTIES = "jedis.properties";

	private static Map<String, JedisProxy> jedisProxyCache = new ConcurrentHashMap<String, JedisProxy>();

	private static Map<String, ShardedJedisProxy> shardedJedisProxyCache = new ConcurrentHashMap<String, ShardedJedisProxy>();

	protected static JedisProxy initJedisProxy(String configProperties) {// 这个过程比较耗时

		JedisProxy jedisProxy = jedisProxyCache.get(configProperties);

		if (jedisProxy != null) {
			return jedisProxy;
		}

		synchronized (AbstractJedisProxyFactory.class) {

			jedisProxy = jedisProxyCache.get(configProperties);
			
			if (jedisProxy != null) {
				return jedisProxy;
			}

			Enhancer enhancer = new Enhancer();
			// 设置需要创建子类的类
			enhancer.setSuperclass(JedisProxy.class);
			enhancer.setCallback(new JedisProxyInteceptor(configProperties));
			// 通过字节码技术动态创建子类实例
			jedisProxy = (JedisProxy) enhancer.create();
			
			jedisProxyCache.put(configProperties, jedisProxy);
			
			return jedisProxy;
		}
	}

	protected static ShardedJedisProxy initShardedJedisProxy(
			String configProperties) {// 这个过程比较耗时

		ShardedJedisProxy shardedJedisProxy = shardedJedisProxyCache.get(configProperties);
		
		if(shardedJedisProxy != null){
			return shardedJedisProxy;
		}
		
		synchronized (AbstractJedisProxyFactory.class) {
			
			shardedJedisProxy = shardedJedisProxyCache.get(configProperties);
			
			if(shardedJedisProxy != null){
				return shardedJedisProxy;
			}
			
			Enhancer enhancer = new Enhancer();
			// 设置需要创建子类的类
			enhancer.setSuperclass(ShardedJedisProxy.class);
			enhancer.setCallback(new JedisProxyInteceptor(configProperties));
			// 通过字节码技术动态创建子类实例
			shardedJedisProxy =  (ShardedJedisProxy) enhancer.create();
			
			shardedJedisProxyCache.put(configProperties, shardedJedisProxy);
			
			return shardedJedisProxy;
		}
	}
}
