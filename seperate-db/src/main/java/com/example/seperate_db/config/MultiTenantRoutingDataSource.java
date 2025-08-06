package com.example.seperate_db.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zaxxer.hikari.HikariDataSource;

public class MultiTenantRoutingDataSource extends AbstractRoutingDataSource {
	private final Map<Object, Object> dataSources = new HashMap<>();

	public MultiTenantRoutingDataSource() {
		dataSources.put("tenant1",
				createDataSource("jdbc:postgresql://localhost:5432/tenant1db", "tenant1user", "tenant1pass"));
		dataSources.put("tenant2",
				createDataSource("jdbc:postgresql://localhost:5432/tenant2db", "tenant2user", "tenant2pass"));

		setDefaultTargetDataSource(
				createDataSource("jdbc:postgresql://localhost:5432/seperate_db", "seperate_db", "seperate_db"));
		setTargetDataSources(dataSources);
		afterPropertiesSet();
	}

	private DataSource createDataSource(String url, String username, String password) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setDriverClassName("org.postgresql.Driver");
		return ds;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return TenantContext.getTenantId();
	}
}