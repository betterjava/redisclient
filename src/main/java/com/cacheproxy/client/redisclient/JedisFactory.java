package com.cacheproxy.client.redisclient;

import net.sf.cglib.proxy.Enhancer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @desc jedis 工厂类
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisFactory {
	
	private static Jedis jedis = initJedisProxy();

	private static ShardedJedis shardedJedis = initShardedJedisProxy();

	/**
	 * 获取 jedis
	 * @return
	 */
	public static Jedis getJedis() {
		return jedis;
	}

	/**
	 * 获取 分片jedis
	 * @return
	 */
	public static ShardedJedis getSharedJedis() {
		return shardedJedis;
	}

	private static Jedis initJedisProxy() {

		Enhancer enhancer = new Enhancer();
		// 设置需要创建子类的类
		enhancer.setSuperclass(Jedis.class);
		enhancer.setCallback(new JedisInteceptor());
		// 通过字节码技术动态创建子类实例
		return (Jedis) enhancer.create();
	}

	private static ShardedJedis initShardedJedisProxy() {

//		Enhancer enhancer = new Enhancer();
//		// 设置需要创建子类的类
//		enhancer.setSuperclass(ShardedJedis.class);
//		enhancer.setCallback(new JedisInteceptor());
//		// 通过字节码技术动态创建子类实例
//		return (ShardedJedis) enhancer.create();
		return null;
	}

}
