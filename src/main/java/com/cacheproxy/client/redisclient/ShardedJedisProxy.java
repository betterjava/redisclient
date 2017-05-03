package com.cacheproxy.client.redisclient;

import java.util.List;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author cookie
 * @email lijiaqiya@163.com
 * @date May 3, 2017
 */
public class ShardedJedisProxy extends ShardedJedis {

	private ShardedJedisProxy(List<JedisShardInfo> shards) {
		super(shards);
	}

	@Override
	public ShardedJedisPipeline pipelined() {
		ShardedJedisPipelineProxy pipeline = new ShardedJedisPipelineProxy(this);
		pipeline.setShardedJedis(this);
		return pipeline;
	}

	@Override
	@Deprecated
	public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline) {
		throw new UnsupportedOperationException();
	}
}
