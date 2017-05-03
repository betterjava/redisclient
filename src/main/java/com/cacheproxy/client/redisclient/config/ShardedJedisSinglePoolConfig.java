package com.cacheproxy.client.redisclient.config;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * @desc 
 * @author liya
 * @emial  lijiaqiya@163.com
 * @date 2017-3-13
 */
public class ShardedJedisSinglePoolConfig extends JedisPoolConfig implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ShardedJedisSinglePoolConfig.class);
	
	private String shards;
	
	public void setShards(String shards) {
		this.shards = shards;
	}
	
	public String getShards() {
		return shards;
	}
	
	public List<JedisShardInfo> getShardList(){
		if(StringUtils.isBlank(shards)){
			return null;
		}
		String[] shardArr = shards.split(";");
		
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		
		
		for(String shardString:shardArr){
			ShardInfoConfig config = new ShardInfoConfig();
			String[] propArr = shardString.split(",");
			for(String propKV:propArr){
				String name = propKV.substring(0,propKV.lastIndexOf(":"));
				String value = propKV.substring(propKV.lastIndexOf(":")+1);
				try {
					BeanUtils.setProperty(config, name, value);
				} catch (IllegalAccessException | InvocationTargetException e) {
					LOGGER.error(e.getMessage(),e);
				}
			}
			JedisShardInfo jedisShardInfo = new JedisShardInfo(config.getHost(), config.getPort(), config.getTimeout(), config.getTimeout(), config.getWeight(), config.isSsl());
			list.add(jedisShardInfo);
		}
		return list;
	}
	
}


