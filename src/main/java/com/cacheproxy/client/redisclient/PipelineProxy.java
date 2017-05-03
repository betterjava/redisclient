package com.cacheproxy.client.redisclient;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author cookie
 * @email lijiaqiya@163.com
 * @date May 3, 2017
 */
public class PipelineProxy extends Pipeline {

	private Jedis jedis;

	public PipelineProxy(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void sync() {
		super.sync();
		jedis.close();
	}

	@Override
	public List<Object> syncAndReturnAll() {
		List<Object> result = super.syncAndReturnAll();
		jedis.close();
		return result;
	}
}
