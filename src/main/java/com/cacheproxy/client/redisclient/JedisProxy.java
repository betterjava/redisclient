package com.cacheproxy.client.redisclient;

import java.util.Collection;
import java.util.List;

import com.cacheproxy.client.redisclient.support.ShardedJedisMoreMethod;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.util.ShardInfo;

/**
 * @desc jedis 代理类
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-14
 */
public class JedisProxy extends Jedis implements ShardedJedisMoreMethod<Jedis, ShardInfo<Jedis>>  {

	@Override
	public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline) {
		return null;
	}

	@Override
	public Collection<ShardInfo<Jedis>> getAllShardInfo() {
		return null;
	}

	@Override
	public Jedis getShard(String key) {
		return null;
	}

	@Override
	public Jedis getShard(byte[] key) {
		return null;
	}

	@Override
	public Collection<Jedis> getAllShards() {
		return null;
	}

	@Override
	public ShardInfo<Jedis> getShardInfo(byte[] key) {
		return null;
	}

	@Override
	public ShardInfo<Jedis> getShardInfo(String key) {
		return null;
	}

	@Override
	public String getKeyTag(String key) {
		return null;
	}
	
}
