package com.cacheproxy.client.redisclient;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cacheproxy.client.redisclient.config.JedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @desc
 * @author liya
 * 
 * @emial lijiaqiya@163.com
 * @date 2017-3-14
 */
public class MethdoInvokeAuthUtil {

	private static Collection<String> jedisMethods = Collections
			.unmodifiableCollection(initMethods(Jedis.class));
	private static Collection<String> shardedJedisMethods = Collections
			.unmodifiableCollection(initMethods(ShardedJedis.class));

	public static boolean canInvoke(Method method) {
		
		boolean shareded = JedisConfig.getInstance().getConfigType().isShareded();
		String methodSignature = getMethodSignature(method);
		if (shareded) {
			return shardedJedisMethods.contains(methodSignature);
		}
		return jedisMethods.contains(methodSignature);
	}

	private static Set<String> initMethods(Class<?> clazz) {
		List<String> methodList = new ArrayList<String>();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			methodList.add(getMethodSignature(method));
		}
		return new HashSet<String>(methodList);
	}

	private static String getMethodSignature(Method method) {
		// TODO
		return null;
	}
}
