package com.cacheproxy.client.redisclient.support.jedis;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author cookie
 * @date May 3, 2017
 */
public class PipelineProxy extends Pipeline {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(PipelineProxy.class);

	private AtomicBoolean hasReturn = new AtomicBoolean();

	private Jedis jedis;

	public PipelineProxy(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void sync() {
		super.sync();

		if (!hasReturn.get()) {
			jedis.close();
			hasReturn.set(true);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("PipelineProxy 释放 连接 ");
			}
		}

	}

	@Override
	public List<Object> syncAndReturnAll() {
		List<Object> result = super.syncAndReturnAll();

		if (!hasReturn.get()) {
			jedis.close();
			hasReturn.set(true);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("PipelineProxy 释放 连接 ");
			}
		}

		return result;
	}

	public void release() {

		if (!hasReturn.get()) {
			jedis.close();
			hasReturn.set(true);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("PipelineProxy 释放 连接 ");
			}
		}
	}

	public void test(){};
}
