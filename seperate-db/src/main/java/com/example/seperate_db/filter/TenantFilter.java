package com.example.seperate_db.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.seperate_db.config.TenantContext;

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
		String tenantId = ((HttpServletRequest) request).getHeader("X-Tenant-ID");
		TenantContext.setTenantId(tenantId);
		chain.doFilter(request, response);
		TenantContext.clear();
	}
}
