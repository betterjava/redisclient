package com.cacheproxy.client.redisclient.support.shardedjedis;

import java.util.ArrayList;
import java.util.List;


import redis.clients.jedis.BinaryShardedJedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author cookie
 * @date May 3, 2017
 */
public class ShardedJedisPipelineWrapper extends BinaryShardedJedis {

	private ShardedJedis shardedJedis;

	public ShardedJedisPipelineWrapper(List<JedisShardInfo> shards) {
		super(new ArrayList<JedisShardInfo>());
	}

	@Override
	public ShardedJedisPipeline pipelined() {
		ShardedJedisPipeline pipeline = new ShardedJedisPipelineProxy(shardedJedis);
		pipeline.setShardedJedis(shardedJedis);
		return pipeline;
	}

	@Override
	@Deprecated
	public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline) {
		throw new UnsupportedOperationException();
	}

	public void setShardedJedis(ShardedJedis shardedJedis) {
		this.shardedJedis = shardedJedis;
	}

}
