package com.cacheproxy.client.redisclient.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cacheproxy.client.redisclient.JedisProxy;
import com.cacheproxy.client.redisclient.JedisProxyFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class TestJedisClient {


	@Test
	public void testjeds() {
		
		while (true) {
		}
	}

	@Test
	public void testMethod() {
		Method[] methods = Jedis.class.getMethods();
		System.out.println(methods.length);
		List<String> methodList = new ArrayList<String>();
		for (Method method : methods) {
			String methodStr = method.toString();
			String pre = methodStr.substring(0, methodStr.lastIndexOf("("));
			// System.out.println(pre);
			String pp = pre.substring(0, pre.lastIndexOf("."));
			// System.out.println(methodStr.substring(pp.length()+1));
			methodList.add(methodStr.substring(pp.length() + 1));
		}
		List<String> methodOtherList = new ArrayList<String>();
		methods = ShardedJedis.class.getMethods();
		System.out.println(methods.length);
		for (Method method : methods) {
			String methodStr = method.toString();
			String pre = methodStr.substring(0, methodStr.lastIndexOf("("));
			// System.out.println(pre);
			String pp = pre.substring(0, pre.lastIndexOf("."));
			 System.out.println(methodStr.substring(pp.length()+1));
			methodOtherList.add(methodStr.substring(pp.length() + 1));
		}
		
//		printListDiff(methodList,methodOtherList);
	}

	private void printListDiff(List<String> methodList,List<String> methodOtherList) {
		for(String method:methodList){
			if(!methodOtherList.contains(method)){
				System.out.println("jedis more than shardedJedis :"+method);
			}
		}
		
		for(String method:methodOtherList){
			if(!methodList.contains(method)){
				System.out.println("shardedJedis."+method);
			}
		}
	}
	
	@Test
	public void testSO(){
		ShardedJedis shardedJedis = null;
		shardedJedis.pipelined(null);// BinaryShardedJedis
		shardedJedis.getAllShardInfo();//Sharded
		shardedJedis.getShard("cc");//Sharded
		shardedJedis.getShard(new byte[1]);//Sharded
		shardedJedis.getAllShards();//Sharded
		shardedJedis.getShardInfo("ss");//Sharded
		shardedJedis.getShardInfo(new byte[1]);
		shardedJedis.getKeyTag("d");//getShard
	}
	
	@Test
	public void testShaobing(){
		GenericObjectPoolConfig poolConfig = null;
		String host = null;
		int port = 0;
		int timeout = 0;
		String password = null;
		int database = 0;
		String clientName = null;
		boolean ssl = false;
		new JedisPool(poolConfig, host, port);
	}
	
	@Test
	public void estshaobing(){
		String masterName;
		Set<String> sentinels;
		GenericObjectPoolConfig poolConfig;
		int connectionTimeout;
		int soTimeout;
		String password;
		int database;
		String clientName;
	}
	@Test
	public void testPipeline() throws IOException{
	}
	
	@Test
	public void testJedisPool(){
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		String key = "cookie";
		String value = "jlkjllk";
		jedisProxy.set(key, value);
		System.out.println(jedisProxy.get(key));
	}
	
	@Test
	public void testJedisPooPipe(){
		
		Logger logger = LoggerFactory.getLogger(TestJedisClient.class);
		System.out.println(logger.isDebugEnabled());
		System.out.println(logger.isInfoEnabled());
		logger.debug("dfdddd");
		logger.info("dfdf");
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		jedisProxy.set("cookie", "jklsjgklsg");
		Pipeline pi = jedisProxy.pipelined();
		String key = "cookie";
		String value = "10000";
		pi.set(key, value);
		pi.get(key);
		List<Object> list = pi.syncAndReturnAll();
		System.out.println(list);
		
	}
	
	@Test
	public void testJedisPooPipe1(){
		
		JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
		int i = 0;
		while(true){
			jedisProxy.set("cookie", i+++"");
		}
	}
}
