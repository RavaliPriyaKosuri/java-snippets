package com.example.seperate_db.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seperate_db.entity.UserDetails;
import com.example.seperate_db.model.LoginRequest;
import com.example.seperate_db.repository.UserRepository;
import com.example.seperate_db.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElse(null);

		if (user == null || !user.getPassword().equals(request.getPassword())) {
			return ResponseEntity.status(401).body("Invalid credentials");
		}

		String token = jwtUtil.generateToken(user.getUsername(), user.getTenantId());

		return ResponseEntity.ok(Collections.singletonMap("token", token));
	}
}
