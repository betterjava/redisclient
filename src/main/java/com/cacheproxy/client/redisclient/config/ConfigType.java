package com.cacheproxy.client.redisclient.config;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public enum ConfigType {
	JedisSingle,			// 单个模式 
	JedisSentinel,			// 哨兵模式
	JedisMasterSlave,		// 主从模式
	ShardedJedis,			// 分片模式
	ShardedJedisSentinel	// 分片哨兵模式
}
