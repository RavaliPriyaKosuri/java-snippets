package com.profile.profileDemoApplication.assignment;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "data")
public class DataProperties {
	private Map<String, String> keyValues;

	public Map<String, String> getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(Map<String, String> keyValues) {
		this.keyValues = keyValues;
	}
}
