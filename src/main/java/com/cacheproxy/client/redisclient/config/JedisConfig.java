package com.cacheproxy.client.redisclient.config;

import java.io.InputStreamReader;
import java.io.Serializable;

import com.google.gson.Gson;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-13
 */
public class JedisConfig implements Serializable {

	private static final long serialVersionUID = -6310458948491215778L;

	public static Gson gson = new Gson();

	private String configType;
	private String config;

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public static JedisConfig loadConfig(String fileName) throws Exception {
		return gson.fromJson(new InputStreamReader(JedisConfig.class
				.getClassLoader().getResourceAsStream(fileName)),
				JedisConfig.class);
	}

}
