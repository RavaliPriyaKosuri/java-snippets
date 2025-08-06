package com.profile.profileDemoApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/query")
	public String holdConnection(@RequestParam(defaultValue = "5") int seconds) {
		jdbcTemplate.queryForObject("SELECT 1 FROM pg_sleep(10)", Integer.class);
		return "Slept for " + seconds + " seconds";
	}
}
