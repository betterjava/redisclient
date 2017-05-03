package com.cacheproxy.client.redisclient.support;

import java.lang.reflect.Method;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-14
 */
public class MethdoInvokeAuthUtil {

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
