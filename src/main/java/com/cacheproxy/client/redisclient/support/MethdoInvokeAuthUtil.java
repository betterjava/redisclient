package com.cacheproxy.client.redisclient.support;

import java.lang.reflect.Method;

/**
 * @desc
 * @author liya
 * 
 * @emial lijiaqiya@163.com
 * @date 2017-3-14
 */
public class MethdoInvokeAuthUtil {

//	private static Collection<String> jedisMethods = Collections
//			.unmodifiableCollection(initMethods(Jedis.class));
//	private static Collection<String> shardedJedisMethods = Collections
//			.unmodifiableCollection(initMethods(ShardedJedis.class));
//
//	/**
//	 * 是否可以调用
//	 * 
//	 * @param method
//	 * @return
//	 */
//	public static boolean canInvoke(Method method) {
//
//		boolean shareded = JedisConfig.getInstance().getConfigType()
//				.isShareded();
//		String methodSignature = getMethodSignature(method);
//		if (shareded) {
//			return shardedJedisMethods.contains(methodSignature);
//		}
//		return jedisMethods.contains(methodSignature);
//	}
//
//	private static Set<String> initMethods(Class<?> clazz) {
//		List<String> methodList = new ArrayList<String>();
//		Method[] methods = clazz.getMethods();
//		for (Method method : methods) {
//			methodList.add(getMethodSignature(method));
//		}
//		return new HashSet<String>(methodList);
//	}
//
	/**
	 * 获取方法签名
	 * 
	 * @param method
	 * @return
	 */
	private static String getMethodSignature(Method method) {
		String methodStr = method.toString();
		String methodName = methodStr.substring(0, methodStr.lastIndexOf("("));
		String methodNamePre = methodName.substring(0,
				methodName.lastIndexOf("."));
		return methodStr.substring(methodNamePre.length() + 1);
	}

	public static boolean isPipeline(Method method) {
		if (method == null) {
			return false;
		}
		return getMethodSignature(method).startsWith("pipelined");
	}
}
