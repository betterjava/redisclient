package com.cacheproxy.client.redisclient.config;

import com.google.gson.Gson;

/**
 * @desc
 * @author liya
 * @emial lijiaqiya@163.com
 * @date 2017-3-14
 */
public class JedisConfig {

	private ConfigType configType;

	private Config config;

	private static JedisConfig jedisConfig = initJedisConfig();

	public static JedisConfig getInstance() {
		return jedisConfig;
	}

	private static JedisConfig initJedisConfig() {
		// 进行异常处理，文件找不到等等 TODO

		JedisConfigGson jedisCofnigGson = JedisConfigGson.loadConfig("jedis.json");

		ConfigType configType = ConfigType.valueOf(jedisCofnigGson.getConfigType());
		JedisConfig jedisConfig = new JedisConfig();
		Gson gson = new Gson();
		switch (configType) {
		case JedisSingle:
			jedisConfig.setConfigType(ConfigType.JedisSingle);
			jedisConfig.setConfig(gson.fromJson(jedisCofnigGson.getConfig(), JedisSinglePoolConfig.class));
			return jedisConfig;
		case JedisSentinel:
			jedisConfig.setConfigType(ConfigType.JedisSentinel);
			jedisConfig.setConfig(gson.fromJson(jedisCofnigGson.getConfig(), JedisSentinelPoolConfig.class));
			return jedisConfig;
		case JedisMasterSlave:
			jedisConfig.setConfigType(ConfigType.JedisMasterSlave);
			jedisConfig.setConfig(gson.fromJson(jedisCofnigGson.getConfig(), JedisMasterSlavePoolConfig.class));
			return jedisConfig;
		case ShardedJedis:
			jedisConfig.setConfigType(ConfigType.ShardedJedis);
			jedisConfig.setConfig(gson.fromJson(jedisCofnigGson.getConfig(), ShardedJedisPoolConfig.class));
			return jedisConfig;
		case ShardedJedisSentinel:
			jedisConfig.setConfigType(ConfigType.ShardedJedisSentinel);
			jedisConfig.setConfig(gson.fromJson(jedisCofnigGson.getConfig(), ShardedJedisSentinelPoolConfig.class));
			return jedisConfig;
		}
		throw new RuntimeException("");
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public ConfigType getConfigType() {
		return configType;
	}

	public void setConfigType(ConfigType configType) {
		this.configType = configType;
	}
}
