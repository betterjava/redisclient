package com.cacheproxy.client.redisclient.test;

import java.lang.reflect.Method;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import com.cacheproxy.client.redisclient.JedisFactory;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class TestJedisClient {

	private static Jedis jedis = JedisFactory.getJedis();

	@Test
	public void testjeds() {
		while (true) {
			jedis.set("cookie", "woshishui");
			System.out.println(jedis.get("cookie"));
		}
	}

	@Test
	public void testMethod() {
		Method[] methods = Jedis.class.getDeclaredMethods();
		System.out.println(methods.length);
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		Method[] methods1 = ShardedJedis.class.getDeclaredMethods();
		System.out.println(methods1.length);
		for (Method method : methods1) {
			System.out.println(method.getName());
		}
//		List<Method> methodsList = Arrays.asList(methods);
//		List<Method> methods1List = Arrays.asList(methods1);
//		System.out.println(methodsList);
//		System.out.println(methods1List);
//		
//		Set<Method> methodsSet = new HashSet(Arrays.asList(methods));
//		Set<Method> methods1Set = new HashSet(Arrays.asList(methods1));
		
	}
	
}
