package com.vinsguru.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.testng.log4testng.Logger;

public class Config {
	
	private static final Logger log = (Logger) LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES = "config/default.properties";
	private static Properties properties;
	
	public static void initilaze() {
		properties = loadProperties();
		for (String key : properties.stringPropertyNames()) {
			if(System.getProperties().containsKey(key)) {
				properties.setProperty(key, System.getProperty(key));
			}
		}
		
	}
	
	public static String get(String key) {
		Config.initilaze();
		return properties.getProperty(key);
	}
	
	private static Properties loadProperties() {
		Properties properties = new Properties();
		try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
			properties.load(stream);
		}catch(Exception e) {
			log.error("Unable to Read Properties file : "+e.getStackTrace());
		}
		return properties;
	}

}
