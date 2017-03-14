package com.cacheproxy.client.redisclient.test;

import java.io.InputStreamReader;

import com.cacheproxy.client.redisclient.config.JedisConfigGson;
import com.google.gson.Gson;

/**
 * @desc 
 * @author liya
 * @emial  lijiaqiya@163.com
 * @date 2017-3-13
 */
public class TestConfig {
	public static void main(String[] args) {
		
		JedisConfigGson config = new JedisConfigGson();
		Gson gson = new Gson();
//		return gson.fromJson(new InputStreamReader(RedisPoolConfig.class.getClassLoader().getResourceAsStream(fileName)), RedisPoolConfig.class);
		JedisConfigGson cc = gson.fromJson(new InputStreamReader(JedisConfigGson.class.getClassLoader().getResourceAsStream("jedis.json")), JedisConfigGson.class);
		System.out.println(cc);
	}
}



