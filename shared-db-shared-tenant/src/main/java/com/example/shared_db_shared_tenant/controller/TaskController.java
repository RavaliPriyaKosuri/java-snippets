package com.example.shared_db_shared_tenant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shared_db_shared_tenant.config.TenantContext;
import com.example.shared_db_shared_tenant.entity.Task;
import com.example.shared_db_shared_tenant.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskRepository repo;

	public TaskController(TaskRepository repo) {
		this.repo = repo;
	}

	@PostMapping
	public Task create(@RequestBody Task task) {
		return repo.save(task);
	}

//	@GetMapping
//	public List<Task> all() {
//		return repo.findAll();
//	}
	
	@GetMapping
	public List<Task> getTasks() {
	    String tenantId = TenantContext.getTenantId();
	    return repo.findAllByTenantId(tenantId);
	}

}
