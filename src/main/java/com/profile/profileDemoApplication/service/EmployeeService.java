package com.profile.profileDemoApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.profile.profileDemoApplication.entity.Employee;
import com.profile.profileDemoApplication.repo.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee save(Employee emp) {
		return repository.save(emp);
	}

	public List<Employee> getAll() {
		return repository.findAll();
	}
}
