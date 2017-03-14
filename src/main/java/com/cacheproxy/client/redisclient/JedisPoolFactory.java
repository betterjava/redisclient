package com.cacheproxy.client.redisclient;

import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import com.cacheproxy.client.redisclient.config.Config;
import com.cacheproxy.client.redisclient.config.ConfigType;
import com.cacheproxy.client.redisclient.config.JedisConfig;
import com.cacheproxy.client.redisclient.config.JedisConfigGson;
import com.cacheproxy.client.redisclient.config.JedisSinglePoolConfig;
import com.google.gson.Gson;

/**
 * @desc jedis 对象池工厂
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisPoolFactory {

	private static Pool jedisPool = initJedisPool();

	public static Pool getJedisPool() {
		return jedisPool;
	}

	private static Pool initJedisPool() {
		
		JedisConfig jedisConfig = JedisConfig.getInstance();
		ConfigType configType = jedisConfig.getConfigType();
		Config config = jedisConfig.getConfig();
		switch (configType) {
		case JedisSingle:
			JedisSinglePoolConfig singleConfig = (JedisSinglePoolConfig) config;
			return new JedisPool(singleConfig,singleConfig.getHost(),singleConfig.getPort());
			// TODO 
		default:
			break;
		}
		return null;
	}
}
