package com.profile.profileDemoApplication.assignment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

	Map<String, String> dev;
	Map<String, String> prod;

	private final Environment environment;
	private final DataProperties dataProperties;

	public DataLoader(Environment environment, DataProperties dataProperties) {
		this.environment = environment;
		this.dataProperties = dataProperties;
	}

	@PostConstruct
	public void loadAndPrintMap() {
		String[] activeProfiles = environment.getActiveProfiles();
		if (activeProfiles.length == 0) {
			System.out.println("No active profile set.");
			return;
		}

		String profile = activeProfiles[0];

		System.out.println("Active Profile: " + profile);
		dev = new HashMap<String, String>();
		dev = dataProperties.getKeyValues();
		System.out.println("HashMap values:");
		dev.forEach((k, v) -> System.out.println(k + " = " + v));

//		else if (profile.equals("prod")) {
//			System.out.println("Active Profile: "+profile);
//			prod = new HashMap<String, String>();
//			prod = dataProperties.getKeyValues();
//			System.out.println("HashMap values:");
//			prod.forEach((k, v) -> System.out.println(k + " = " + v));
//		}
	}
}
