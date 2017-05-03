package com.cacheproxy.client.redisclient.support;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

import com.cacheproxy.client.redisclient.config.ConfigType;
import com.cacheproxy.client.redisclient.config.JedisSentinelPoolConfig;
import com.cacheproxy.client.redisclient.config.JedisSinglePoolConfig;
import com.cacheproxy.client.redisclient.config.ShardedJedisSentinelPoolConfig;
import com.cacheproxy.client.redisclient.config.ShardedJedisSinglePoolConfig;
import com.cacheproxy.client.redisclient.pool.extend.ShardedJedisSentinelPool;

/**
 * @desc jedis 对象池工厂
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisPoolFactory {

	public static Pool<?> initJedisPool(String configProperties) {
		
		Properties properties = PropertiesUtils.load(configProperties);
		
		ConfigType configType = getConfigType(properties);
		
		switch (configType) {

		case JedisSingle:
			JedisSinglePoolConfig singleConfig = new JedisSinglePoolConfig();
			PropertiesUtils.setProperties(singleConfig,properties);
			return new JedisPool(singleConfig, singleConfig.getHost(),
					singleConfig.getPort(), singleConfig.getTimeout(),
					singleConfig.getPassword(), singleConfig.getDatabase(),
					singleConfig.getClientName(), singleConfig.isSsl());
		case JedisSentinel:
			JedisSentinelPoolConfig sentinelPoolConfig = new JedisSentinelPoolConfig();
			PropertiesUtils.setProperties(sentinelPoolConfig,properties);
			return new JedisSentinelPool(sentinelPoolConfig.getMasterName(),
					sentinelPoolConfig.getSentinels(), sentinelPoolConfig,
					sentinelPoolConfig.getTimeout(),
					sentinelPoolConfig.getTimeout(),
					sentinelPoolConfig.getPassword(),
					sentinelPoolConfig.getDatabase(),
					sentinelPoolConfig.getClientName());
		case ShardedJedis:
			ShardedJedisSinglePoolConfig shardedJedisSinglePoolConfig = new ShardedJedisSinglePoolConfig();
			PropertiesUtils.setProperties(shardedJedisSinglePoolConfig,properties);
			return new ShardedJedisPool(shardedJedisSinglePoolConfig,
					shardedJedisSinglePoolConfig.getShards());
		case ShardedJedisSentinel:
			ShardedJedisSentinelPoolConfig shardedJedisSentinelPoolConfig = new ShardedJedisSentinelPoolConfig();
			PropertiesUtils.setProperties(shardedJedisSentinelPoolConfig,properties);
			return new ShardedJedisSentinelPool(
					shardedJedisSentinelPoolConfig.getMasters(),
					shardedJedisSentinelPoolConfig.getSentinels(),
					shardedJedisSentinelPoolConfig,
					shardedJedisSentinelPoolConfig.getTimeout(),
					shardedJedisSentinelPoolConfig.getPassword(),
					shardedJedisSentinelPoolConfig.getDatabase());
		}
		throw new RuntimeException("can not find correct configType from jedis config ");
	}

	private static ConfigType getConfigType(Properties properties) {
		
		if(properties.get("masters") != null && properties.get("masters") != StringUtils.EMPTY){
			return ConfigType.ShardedJedisSentinel;
		}else if(properties.get("master") != null && properties.get("master") != StringUtils.EMPTY){
			return ConfigType.JedisSentinel;
		}else if(properties.get("shards") != null && properties.get("shards") != StringUtils.EMPTY){
			return ConfigType.ShardedJedis;
		}else{
			return ConfigType.JedisSingle;
		}
	}
}
