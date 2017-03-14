package com.cacheproxy.client.redisclient;

import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import com.cacheproxy.client.redisclient.config.ConfigType;
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
		// 从配置文件中获取--一共有5中连接池：只提供这简单的集中
		/**
		 * 1.jedisPool 
		 * 2.哨兵pool
		 * 3.分片pool
		 * 4.哨兵+分片
		 * 5.主从
		 */
//		jedisPool
//		JedisSentinelPool
//		ShardedJedisPool
//		ShardedJedisSentinelPool
//		JedisMasterSlavePool
		
		/**
		 * 按照格式来拆分
		 */
		JedisConfigGson config = null;
		try {
			config = JedisConfigGson.loadConfig("jedis.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(config.getConfigType().equals(ConfigType.JedisSingle.name())){
			JedisSinglePoolConfig redisConfig = new Gson().fromJson(config.getConfig(), JedisSinglePoolConfig.class);
			return new JedisPool(redisConfig,redisConfig.getHost(),redisConfig.getPort());
		}
		
		return null;
	}
}
