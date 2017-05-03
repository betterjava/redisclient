package com.cacheproxy.client.redisclient.support.shardedjedis;
//package com.cacheproxy.client.redisclient.support;
//
//import java.util.Collection;
//import java.util.List;
//
//import redis.clients.jedis.ShardedJedisPipeline;
//import redis.clients.util.ShardInfo;
//
///**
// * @desc   分片 jedis 比 jedis 多出来的方法
// * @author liya
// * @emial lijiaqiya@163.com
// * @date 2017-3-14
// */
//public interface ShardedJedisMoreMethod<R, S extends ShardInfo<R>> {
//
//	List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline);
//
//	Collection<S> getAllShardInfo();
//
//	R getShard(String key);
//
//	R getShard(byte[] key);
//
//	Collection<R> getAllShards();
//
//	S getShardInfo(byte[] key);
//
//	S getShardInfo(String key);
//
//	String getKeyTag(String key);
//
//}
