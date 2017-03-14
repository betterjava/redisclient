package com.cacheproxy.client.redisclient.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import com.cacheproxy.client.redisclient.JedisFactory;
import com.cacheproxy.client.redisclient.JedisProxy;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class TestJedisClient {

	private static JedisProxy jedis = JedisFactory.getJedisProxy();

	@Test
	public void testjeds() {
//		jedis.getShard("cookie");
		while (true) {
			jedis.set("cookie", "woshishui");
			System.out.println(jedis.get("cookie"));
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
	
	
	
}
