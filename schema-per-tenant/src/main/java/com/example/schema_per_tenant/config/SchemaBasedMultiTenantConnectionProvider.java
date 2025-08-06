package com.example.schema_per_tenant.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

public class SchemaBasedMultiTenantConnectionProvider implements MultiTenantConnectionProvider<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DataSource dataSource;

	public SchemaBasedMultiTenantConnectionProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public boolean isUnwrappableAs(Class<?> unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	@Override
	public Connection getConnection(Object tenantIdentifier) throws SQLException {
		final Connection connection = dataSource.getConnection();
		String tenant = tenantIdentifier.toString();
		connection.setSchema(tenant); // PostgreSQL-specific
		return connection;
	}

	@Override
	public void releaseConnection(Object tenantIdentifier, Connection connection) throws SQLException {
		connection.close();

	}
}
