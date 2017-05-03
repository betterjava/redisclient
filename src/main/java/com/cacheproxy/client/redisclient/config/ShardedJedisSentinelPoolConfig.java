package com.cacheproxy.client.redisclient.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class ShardedJedisSentinelPoolConfig extends JedisPoolConfig implements Serializable{

	private String masters;
	
	private String sentinels;
	
	private static final long serialVersionUID = 1L;

	private int timeout = Protocol.DEFAULT_TIMEOUT;

	private String password = null;

	private int database = Protocol.DEFAULT_DATABASE;

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public List<String> getMasters() {
		if (StringUtils.isBlank(masters)) {
			return null;
		}
		List<String> masterList = new ArrayList<>();
		String[] arr = masters.split(",");
		for (String master : arr) {
			masterList.add(master.trim());
		}
		return masterList;
	}
	
	public void setMasters(String masters) {
		this.masters = masters;
	}

	public Set<String> getSentinels() {
		if (StringUtils.isBlank(sentinels)) {
			return null;
		}
		Set<String> sentinelList = new HashSet<>();
		String[] arr = sentinels.split(",");
		for (String master : arr) {
			sentinelList.add(master.trim());
		}
		return sentinelList;
	}
}
