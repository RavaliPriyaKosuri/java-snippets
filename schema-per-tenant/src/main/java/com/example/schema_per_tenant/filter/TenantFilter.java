package com.example.schema_per_tenant.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.schema_per_tenant.config.TenantContext;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class TenantFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String tenantId = httpRequest.getHeader("X-Tenant-ID");
			if (tenantId != null) {
				TenantContext.setTenantId(tenantId);
			}
			chain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
	}
}
