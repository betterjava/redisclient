package com.cacheproxy.client.redisclient.support;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cookie
 * @date May 3, 2017
 */
public class PropertiesUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

	public static Properties load(String configPath) {
		try {
			InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(configPath);
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		} catch (IOException e) {
			LOGGER.error("读取配置文件出错", e);
			throw new RuntimeException(e);
		}
	}

	public static void setProperties(Object bean, Properties properties) {
		Iterator<Object> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			String name = String.valueOf(iterator.next());
			Object value = properties.get(name);
			try {
				BeanUtils.setProperty(bean, name, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
}
