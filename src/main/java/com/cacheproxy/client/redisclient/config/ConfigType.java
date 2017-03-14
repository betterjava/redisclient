package com.cacheproxy.client.redisclient.config;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public enum ConfigType {
	JedisSingle(false),				// 单个模式 
	JedisSentinel(false),			// 哨兵模式
	JedisMasterSlave(false),		// 主从模式
	ShardedJedis(true),				// 分片模式
	ShardedJedisSentinel(true);		// 分片哨兵模式
	
	private boolean shareded;
	
	ConfigType( boolean shareded){
		this.shareded = shareded;
	}
	
	public boolean isShareded() {
		return shareded;
	}
}
