package com.cacheproxy.client.redisclient;

import java.util.ArrayList;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

/**
 * @author cookie
 * @email lijiaqiya@163.com
 * @date May 3, 2017
 */
public class ShardedJedisProxy extends ShardedJedis {

	public ShardedJedisProxy(){
		super(new ArrayList<JedisShardInfo>());
	}
}
