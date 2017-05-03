package com.cacheproxy.client.redisclient;

import java.util.List;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author cookie
 * @email lijiaqiya@163.com
 * @date May 3, 2017
 */
public class ShardedJedisPipelineProxy extends ShardedJedisPipeline {

	private ShardedJedis shardedJedis;

	public ShardedJedisPipelineProxy(ShardedJedis shardedJedis) {
		this.shardedJedis = shardedJedis;
	}

	@Override
	public void sync() {
		super.sync();
		shardedJedis.close();

	}

	@Override
	public List<Object> syncAndReturnAll() {

		List<Object> result = super.syncAndReturnAll();
		shardedJedis.close();
		return result;
	}
}
