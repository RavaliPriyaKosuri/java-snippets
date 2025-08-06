package com.example.schema_per_tenant.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver<Object> {

	private static final String DEFAULT_TENANT = "public";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenantId = TenantContext.getTenantId();
		return (tenantId != null) ? tenantId : DEFAULT_TENANT;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
