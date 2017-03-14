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
public class JedisConfigGson implements Serializable {

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

	public static JedisConfigGson loadConfig(String fileName) {
		return gson.fromJson(new InputStreamReader(JedisConfigGson.class
				.getClassLoader().getResourceAsStream(fileName)),
				JedisConfigGson.class);
	}

}
