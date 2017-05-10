package com.cacheproxy.client.redisclient;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.Transaction;

import com.cacheproxy.client.redisclient.support.jedis.PipelineProxy;
import com.cacheproxy.client.redisclient.support.jedis.TransactionProxy;
import com.cacheproxy.client.redisclient.support.shardedjedis.ShardedJedisPipelineProxy;

/**
 * @author cookie
 * @date   May 4, 2017
 */
public class ReleaseUtil {

	public static void realease(Pipeline pipeline) {
		if (pipeline == null || !(pipeline instanceof PipelineProxy)) {
			return;
		}
		PipelineProxy proxy = (PipelineProxy) pipeline;
		proxy.release();
	}

	public static void realease(Transaction transaction) {

		if (transaction == null || !(transaction instanceof TransactionProxy)) {
			return;
		}
		TransactionProxy proxy = (TransactionProxy) transaction;
		proxy.release();

	}

	public static void realease(ShardedJedisPipeline shardedJedisPipeline) {

		if (shardedJedisPipeline == null|| !(shardedJedisPipeline instanceof ShardedJedisPipelineProxy)) {
			return;
		}
		ShardedJedisPipelineProxy proxy = (ShardedJedisPipelineProxy) shardedJedisPipeline;
		proxy.release();

	}

}


