package com.example.seperate_db.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.seperate_db.config.TenantContext;
import com.example.seperate_db.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTenantFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, java.io.IOException {

		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			String tenantId = jwtUtil.extractTenantId(jwt);
			System.out.println("Extracted Tenant ID: " + tenantId); 
			TenantContext.setTenantId(tenantId);
		}
		try {
			filterChain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
	}
}
