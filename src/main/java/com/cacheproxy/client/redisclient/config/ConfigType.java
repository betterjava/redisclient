package com.cacheproxy.client.redisclient.config;

/**
 * @author cookie
 * @date   May 3, 2017
 */
public enum ConfigType {
	JedisSingle,				// 单个模式 
	JedisSentinel,				// 哨兵模式
	ShardedJedis,				// 分片模式
	ShardedJedisSentinel;		// 分片哨兵模式
}


