package com.example.seperate_db.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zaxxer.hikari.HikariDataSource;

public class MultiTenantRoutingDataSource extends AbstractRoutingDataSource {

	private final Map<Object, Object> dataSourceMap = new HashMap<>();

	public MultiTenantRoutingDataSource() {

		HikariDataSource defaultDataSource = new HikariDataSource();
		defaultDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/seperate_db");
		defaultDataSource.setUsername("seperate_db");
		defaultDataSource.setPassword("seperate_db");
		defaultDataSource.setDriverClassName("org.postgresql.Driver");

		// Add tenant1
		HikariDataSource tenant1DataSource = new HikariDataSource();
		tenant1DataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/tenant1db");
		tenant1DataSource.setUsername("tenant1user");
		tenant1DataSource.setPassword("tenant1pass");
		tenant1DataSource.setDriverClassName("org.postgresql.Driver");

		// Add tenant2
		HikariDataSource tenant2DataSource = new HikariDataSource();
		tenant2DataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/tenant2db");
		tenant2DataSource.setUsername("tenant2user");
		tenant2DataSource.setPassword("tenant2pass");
		tenant2DataSource.setDriverClassName("org.postgresql.Driver");

		dataSourceMap.put("tenant1", tenant1DataSource);
		dataSourceMap.put("tenant2", tenant2DataSource);

		// Very important: set both
		this.setTargetDataSources(dataSourceMap);
		this.setDefaultTargetDataSource(defaultDataSource);
		this.afterPropertiesSet(); // must call this
	}

	@Override
	protected Object determineCurrentLookupKey() {
		String tenantId = TenantContext.getTenantId();
		System.out.println("Extracted Tenant ID: " + tenantId);
		System.out.println("Routing to tenant DB: " + tenantId + ", Exists: " + dataSourceMap.containsKey(tenantId));
		return tenantId;
	}
}
