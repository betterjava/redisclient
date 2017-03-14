package com.cacheproxy.client.redisclient.support;

import java.io.Closeable;
import java.lang.reflect.Method;

import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import com.cacheproxy.client.redisclient.config.Config;
import com.cacheproxy.client.redisclient.config.ConfigType;
import com.cacheproxy.client.redisclient.config.JedisConfig;
import com.cacheproxy.client.redisclient.config.JedisSinglePoolConfig;
import com.cacheproxy.client.redisclient.pool.extend.JedisMasterSlavePool;

/**
 * @desc jedis 对象池工厂
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisFactory {

	private static Pool<?> jedisPool = initJedisPool();

	public static Closeable getJedis(Method method) {
		if (JedisConfig.getInstance().getConfigType().equals(ConfigType.JedisMasterSlave)) {
			
			return ((JedisMasterSlavePool) jedisPool).getResource(method);
		}
		return (Closeable) jedisPool.getResource();
	}

	
	private static Pool<?> initJedisPool() {
		
		JedisConfig jedisConfig = JedisConfig.getInstance();
		ConfigType configType = jedisConfig.getConfigType();
		Config config = jedisConfig.getConfig();
		
		switch (configType) {
		
		case JedisSingle:
			JedisSinglePoolConfig singleConfig = (JedisSinglePoolConfig) config;
			return new JedisPool(singleConfig,singleConfig.getHost(),singleConfig.getPort());
		case JedisSentinel:
			
		default:
			break;
		}
		return null;
	}
}
