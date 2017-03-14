package com.cacheproxy.client.redisclient.config;

import java.io.Serializable;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


/**
 * @desc 
 * @author liya
 * @emial  lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisSinglePoolConfig extends GenericObjectPoolConfig implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String host = "127.0.0.1";
	private int port = 6379;
	
	private GenericObjectPoolConfig config;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	public int getPort() {
		return port;
	}
	
}


