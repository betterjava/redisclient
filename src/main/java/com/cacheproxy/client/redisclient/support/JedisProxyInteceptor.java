package com.cacheproxy.client.redisclient.support;

import java.io.Closeable;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Pool;

import com.cacheproxy.client.redisclient.support.jedis.JedisPipelineWrapper;
import com.cacheproxy.client.redisclient.support.jedis.JedisTransactionWrapper;
import com.cacheproxy.client.redisclient.support.shardedjedis.ShardedJedisPipelineWrapper;

/**
 * @desc 代理类管理器
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisProxyInteceptor implements MethodInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisProxyInteceptor.class);
	
	private  Pool<?> jedisPool;
	
	public JedisProxyInteceptor(String configProperties){
		initJedisPool(configProperties);
	}
	
	private void initJedisPool(String configProperties) {
		jedisPool = JedisPoolFactory.initJedisPool(configProperties);
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {

		Closeable closeAble = null;
		boolean isExclusive = false;// 独占连接
		try {
			
			boolean isPipeline = MethdoInvokeAuthUtil.isPipeline(method);
			boolean isMulti = MethdoInvokeAuthUtil.isMulti(method);
			
			isExclusive = isPipeline || isMulti;
			
			closeAble = (Closeable) jedisPool.getResource();
			
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("intercept 获取 redis 连接...");
			}
			
			if(!isExclusive){
				return method.invoke(closeAble, args);
			}
			
			if(closeAble instanceof Jedis){
				if(isPipeline){
					return method.invoke(new JedisPipelineWrapper((Jedis) closeAble), args);
				}
				if(isMulti){
					return method.invoke(new JedisTransactionWrapper((Jedis) closeAble), args);
				}
			}else{
				ShardedJedisPipelineWrapper pipelineWrapper = new ShardedJedisPipelineWrapper(null);
				pipelineWrapper.setShardedJedis((ShardedJedis) closeAble);
				return method.invoke(pipelineWrapper, args);	
			}
			return method.invoke(closeAble, args);
		} finally {
			if (closeAble != null && !isExclusive) {
				closeAble.close();
				
				if(LOGGER.isDebugEnabled()){
					LOGGER.debug("intercept 释放 redis 连接...");
				}
			}
		}
	}
}
