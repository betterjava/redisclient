package com.cacheproxy.client.redisclient.config;

import java.io.Serializable;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * @desc 
 * @author liya
 * @emial  lijiaqiya@163.com
 * @date 2017-3-13
 */
public class ShardedJedisSinglePoolConfig extends JedisPoolConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public List<JedisShardInfo> getShards(){
		return null;
	}

}


