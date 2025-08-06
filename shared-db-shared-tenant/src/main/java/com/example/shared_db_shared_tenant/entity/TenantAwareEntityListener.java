package com.example.shared_db_shared_tenant.entity;

import com.example.shared_db_shared_tenant.config.TenantContext;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class TenantAwareEntityListener {

	@PrePersist
	@PreUpdate
	public void setTenant(Object entity) {
		if (entity instanceof Task) {
			Task task = (Task) entity;
			String tenantId = TenantContext.getTenantId();
			task.setTenantId(tenantId);
		}
	}
}
