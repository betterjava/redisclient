package com.cacheproxy.client.redisclient.support.jedis;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * @author cookie
 * @date May 4, 2017
 */
public class TransactionProxy extends Transaction {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

	private Jedis jedis;
	
	private AtomicBoolean hasReturn = new AtomicBoolean();

	public TransactionProxy(Jedis jedis) {
		super(jedis.getClient());
		this.jedis = jedis;
	}

	@Override
	public List<Object> exec() {
		
		List<Object> result = super.exec();
		
		if (!hasReturn.get()) {
			jedis.close();
			hasReturn.set(true);
			
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("TransactionProxy 释放 redis 连接...");
			}
		}
		
		
		return result;
	}

	@Override
	public List<Response<?>> execGetResponse() {
		
		List<Response<?>> result = super.execGetResponse();
		
		if (!hasReturn.get()) {
			jedis.close();
			hasReturn.set(true);
			
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("TransactionProxy 释放 redis 连接...");
			}
		}
		
		return result;
	}

	@Override
	public String discard() {
		String result = super.discard();
		
		if (!hasReturn.get()) {
			jedis.close();
			hasReturn.set(true);
			
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("TransactionProxy 释放 redis 连接...");
			}
			
		}
		
		return result;
	}
	
	public void release() {

		if (!hasReturn.get()) {
			jedis.close();
			hasReturn.set(true);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("TransactionProxy 释放 连接 ");
			}
		}
	}
}
