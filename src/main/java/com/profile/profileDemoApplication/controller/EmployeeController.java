package com.profile.profileDemoApplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profile.profileDemoApplication.entity.Employee;
import com.profile.profileDemoApplication.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping
	public Employee add(@RequestBody Employee emp) {
		return service.save(emp);
	}

	@GetMapping
	public List<Employee> list() {
		return service.getAll();
	}
}
