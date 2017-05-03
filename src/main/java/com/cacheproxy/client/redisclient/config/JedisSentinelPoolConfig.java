package com.cacheproxy.client.redisclient.config;

import java.io.Serializable;
import java.util.Set;

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
	
	private String masterName;
	private Set<String> sentinels ;
	private int timeout = Protocol.DEFAULT_TIMEOUT;
	private String password = null;
	private int database = Protocol.DEFAULT_DATABASE;
	private String clientName = null;
	
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public Set<String> getSentinels() {
		return sentinels;
	}
	public void setSentinels(Set<String> sentinels) {
		this.sentinels = sentinels;
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


