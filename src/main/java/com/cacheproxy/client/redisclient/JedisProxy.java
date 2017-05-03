package com.cacheproxy.client.redisclient;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.PipelineBlock;

/**
 * @desc jedis 代理类
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-14
 */
public class JedisProxy extends Jedis   {
	
	@Override
	public Pipeline pipelined() {
		pipeline = new PipelineProxy(this);
		pipeline.setClient(client);
		return pipeline;
	}
	
	@Override
	@Deprecated
	public List<Object> pipelined(PipelineBlock jedisPipeline) {
		throw new UnsupportedOperationException();
	}
}
