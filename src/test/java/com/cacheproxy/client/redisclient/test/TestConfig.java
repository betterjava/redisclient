package com.cacheproxy.client.redisclient.test;

import java.io.InputStreamReader;

import com.cacheproxy.client.redisclient.config.JedisConfig;
import com.google.gson.Gson;

/**
 * @desc 
 * @author liya
 * @emial  lijiaqiya@163.com
 * @date 2017-3-13
 */
public class TestConfig {
	public static void main(String[] args) {
		
		JedisConfig config = new JedisConfig();
		Gson gson = new Gson();
//		return gson.fromJson(new InputStreamReader(RedisPoolConfig.class.getClassLoader().getResourceAsStream(fileName)), RedisPoolConfig.class);
		JedisConfig cc = gson.fromJson(new InputStreamReader(JedisConfig.class.getClassLoader().getResourceAsStream("jedis.json")), JedisConfig.class);
		System.out.println(cc);
	}
}



