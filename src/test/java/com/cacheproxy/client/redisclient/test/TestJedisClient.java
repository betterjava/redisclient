package com.cacheproxy.client.redisclient.test;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedisPipeline;

import com.cacheproxy.client.redisclient.JedisProxy;
import com.cacheproxy.client.redisclient.JedisProxyFactory;
import com.cacheproxy.client.redisclient.ShardedJedisProxy;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class TestJedisClient {


	@Test
	public void testJedisProxy() {
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		jedisProxy.set("cookie", "hello");
		Assert.assertEquals("hello", jedisProxy.get("cookie"));
	}
	
	@Test
	public void testJedisProxyPipeline() {
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		Pipeline pi = jedisProxy.pipelined();
		pi.set("cookie", "helloboy");
		pi.get("cookie");
		System.out.println(pi.syncAndReturnAll());
	}
	
	@Test
	public void testShardJedisProxy(){
		ShardedJedisProxy proxy = JedisProxyFactory.createShardedJedisProxy("myredis.properties");
		proxy.set("cookie", "girl");
		Assert.assertEquals("hello", proxy.get("cookie"));
	}
	
	@Test
	public void testShardJedisProxyPipeline(){
		ShardedJedisProxy proxy = JedisProxyFactory.createShardedJedisProxy("myredis.properties");
		ShardedJedisPipeline pi = proxy.pipelined();
		pi.set("cookie", "xiaoniaoyiren");
		pi.get("cookie");
		System.out.println(pi.syncAndReturnAll());
	}
}
