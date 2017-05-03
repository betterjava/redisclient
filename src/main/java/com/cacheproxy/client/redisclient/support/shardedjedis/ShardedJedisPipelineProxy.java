package com.cacheproxy.client.redisclient.support.shardedjedis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author cookie
 * @email lijiaqiya@163.com
 * @date May 3, 2017
 */
public class ShardedJedisPipelineProxy extends ShardedJedisPipeline {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ShardedJedisPipelineProxy.class);

	private ShardedJedis shardedJedis;

	public ShardedJedisPipelineProxy(ShardedJedis shardedJedis) {
		this.shardedJedis = shardedJedis;
	}

	@Override
	public void sync() {
		super.sync();
		shardedJedis.close();
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("ShardedJedisPipelineProxy 释放 连接 ");
		}
	}

	@Override
	public List<Object> syncAndReturnAll() {

		List<Object> result = super.syncAndReturnAll();
		shardedJedis.close();
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("ShardedJedisPipelineProxy 释放 连接 ");
		}
		
		return result;
	}
}
