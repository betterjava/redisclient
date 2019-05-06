package com.cacheproxy.client.redisclient.support.jedis;

import java.util.List;

import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.TransactionBlock;

/**
 * @author cookie
 * @date   May 4, 2017
 */
public class JedisTransactionWrapper extends BinaryJedis {

	private Jedis jedis;
	
	public JedisTransactionWrapper(Jedis jedis) {
		this.jedis = jedis;
	}
	
	@Override
	public Transaction multi() {
		jedis.getClient().multi();
		Transaction transaction = new TransactionProxy(jedis);
	    return transaction;
	}
	
	@Override
	@Deprecated
	public List<Object> multi(TransactionBlock jedisTransaction) {
		throw new UnsupportedOperationException();
	}
	
}


