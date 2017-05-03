package com.cacheproxy.client.redisclient.support.jedis;

import java.util.List;


import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.PipelineBlock;

/**
 * @author cookie
 * @email lijiaqiya@163.com
 * @date May 3, 2017
 */
public class JedisPipelineWrapper extends BinaryJedis {

	private Jedis jedis;

	public JedisPipelineWrapper(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public Pipeline pipelined() {
		Pipeline pipeline = new PipelineProxy(jedis);
		pipeline.setClient(jedis.getClient());
		return pipeline;
	}
	
	@Override
	public List<Object> pipelined(PipelineBlock jedisPipeline) {
		throw new UnsupportedOperationException();
	}

}
