package com.example.seperate_db.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "mysecurekeythatismorethan32chars!!"; // Must be 32+ chars
	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	public String generateToken(String username, String tenantId) {
		return Jwts.builder().setSubject(username).claim("tenantId", tenantId).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
				.signWith(key).compact();
	}

	public String extractTenantId(String token) {
		return extractAllClaims(token).get("tenantId", String.class);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
}
