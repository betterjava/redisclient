package com.cacheproxy.client.redisclient.pool.extend;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import com.cacheproxy.client.redisclient.support.WriteReadJudge;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisMasterSlavePool extends Pool<Jedis> {

	private JedisPool master;
	private List<JedisPool> slaves;
	private int slaveSize = 0;
	private static final Random RANDOM = new Random();

	public Jedis getResource(Method method) {
		boolean isWrite = WriteReadJudge.isWrite(method);
		if (isWrite) {
			return master.getResource();
		}
		return slaves.get(RANDOM.nextInt(slaveSize)).getResource();
	}
}
