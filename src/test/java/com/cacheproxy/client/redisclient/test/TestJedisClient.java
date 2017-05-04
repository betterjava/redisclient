package com.cacheproxy.client.redisclient.test;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.Transaction;

import com.cacheproxy.client.redisclient.JedisProxy;
import com.cacheproxy.client.redisclient.JedisProxyFactory;
import com.cacheproxy.client.redisclient.ShardedJedisProxy;
import com.cacheproxy.client.redisclient.support.ReleaseUtil;

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
		try {
			pi.set("cookie", "helloboy");
			pi.get("cookie");
			System.out.println(pi.syncAndReturnAll());
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			ReleaseUtil.realease(pi);
		}
		
	}
	
	@Test
	public void testShardJedisProxy(){
		ShardedJedisProxy proxy = JedisProxyFactory.createShardedJedisProxy("myredis.properties");
		proxy.set("cookie", "girl");
		Assert.assertEquals("girl", proxy.get("cookie"));
	}
	
	@Test
	public void testShardJedisProxyPipeline(){
		ShardedJedisProxy proxy = JedisProxyFactory.createShardedJedisProxy("myredis.properties");
		ShardedJedisPipeline pi = proxy.pipelined();
		pi.set("cookie", "xiaoniaoyiren");
		pi.get("cookie");
		System.out.println(pi.syncAndReturnAll());
	}
	
	@Test
	public void testTr(){
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		Transaction tt = null;
		try {
			tt = jedisProxy.multi();
			tt.set("cookie", "10000");
			tt.exec();
		} finally{
			ReleaseUtil.realease(tt);
		}
	}
	
	@Test
	public void testJedisProxy1(){
		
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		for (int i = 0; i < 100; i++) {
			jedisProxy.set("cookie", i+"");
			System.out.println(jedisProxy.get("cookie"));
		}
	}
	
	@Test
	public void testJedisProxyPipeline1(){
		
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		jedisProxy = JedisProxyFactory.createJedisProxy();
		jedisProxy = JedisProxyFactory.createJedisProxy();
		
		for (int i = 0; i < 100; i++) {
			Pipeline pp = jedisProxy.pipelined();
			try {
				pp.set("cookie", i+"");
				pp.get("cookie");
				System.out.println(pp.syncAndReturnAll());
			} finally{
				ReleaseUtil.realease(pp);
			}
		}
	}
	
}
