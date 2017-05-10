package com.cacheproxy.client.redisclient.config;

import java.io.Serializable;

import redis.clients.jedis.Protocol;
import redis.clients.util.Sharded;

/**
 * @author cookie
 * @date   May 3, 2017
 */
public class ShardInfoConfig implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String host = Protocol.DEFAULT_HOST;
	private int port = Protocol.DEFAULT_PORT;
	private int timeout = Protocol.DEFAULT_TIMEOUT;
	private int weight = Sharded.DEFAULT_WEIGHT;
	private boolean ssl = false;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public boolean isSsl() {
		return ssl;
	}
	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}
}


