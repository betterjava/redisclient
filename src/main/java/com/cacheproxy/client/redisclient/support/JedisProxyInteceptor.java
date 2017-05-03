package com.cacheproxy.client.redisclient.support;

import java.io.Closeable;
import java.lang.reflect.Method;

import com.cacheproxy.client.redisclient.support.jedis.JedisPipelineWrapper;
import com.cacheproxy.client.redisclient.support.shardedjedis.ShardedJedisPipelineWrapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @desc 代理类管理器
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisProxyInteceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {

		Closeable closeAble = null;
		boolean isPipeline = false;
		try {
			isPipeline = MethdoInvokeAuthUtil.isPipeline(method);
			
			closeAble = JedisFactory.getJedis();
			
			if(!isPipeline){
				return method.invoke(closeAble, args);
			}
			if(closeAble instanceof Jedis){
				return method.invoke(new JedisPipelineWrapper((Jedis) closeAble), args);
			}else{
				ShardedJedisPipelineWrapper pipelineWrapper = new ShardedJedisPipelineWrapper(null);
				pipelineWrapper.setShardedJedis((ShardedJedis) closeAble);
				return method.invoke(pipelineWrapper, args);
			}
		} finally {
			if (closeAble != null && !isPipeline) {
				closeAble.close();
			}
		}
	}
}
