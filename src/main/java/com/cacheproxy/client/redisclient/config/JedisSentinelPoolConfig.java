package com.cacheproxy.client.redisclient.config;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * @desc 
 * @author liya
 * @emial  lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisSentinelPoolConfig extends JedisPoolConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String master;
	private String sentinels ;
	private int timeout = Protocol.DEFAULT_TIMEOUT;
	private String password = null;
	private int database = Protocol.DEFAULT_DATABASE;
	private String clientName = null;
	
	public Set<String> getSentinelSet() {
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
	
	public String getSentinels() {
		return sentinels;
	}
	public void setSentinels(String sentinels) {
		this.sentinels = sentinels;
	}
	
	public String getMaster() {
		return master;
	}
	
	public void setMaster(String master) {
		this.master = master;
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDatabase() {
		return database;
	}
	public void setDatabase(int database) {
		this.database = database;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}


