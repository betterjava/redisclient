package com.cacheproxy.client.redisclient.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import com.cacheproxy.client.redisclient.config.JedisConfig;

/**
 * @desc
 * @author liya
 * 
 * @emial lijiaqiya@163.com
 * @date 2017-3-14
 */
public class MethdoInvokeAuthUtil {

	private static Collection<String> jedisMethods = Collections.unmodifiableCollection(initMethods(Jedis.class));
	private static Collection<String> shardedJedisMethods = Collections.unmodifiableCollection(initMethods(ShardedJedis.class));

	/**
	 * 是否可以调用
	 * @param method
	 * @return
	 */
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

	/**
	 * 获取方法签名
	 * @param method
	 * @return
	 */
	private static String getMethodSignature(Method method) {
		String methodStr = method.toString();
		String methodName = methodStr.substring(0, methodStr.lastIndexOf("("));
		String methodNamePre = methodName.substring(0,methodName.lastIndexOf("."));
		return methodStr.substring(methodNamePre.length() + 1);
	}
}
