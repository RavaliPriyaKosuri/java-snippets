package com.example.seperate_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seperate_db.entity.Task;
import com.example.seperate_db.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository repo;

	@PostMapping
	public Task create(@RequestBody Task task) {
		return repo.save(task);
	}

	@GetMapping
	public List<Task> getAll() {
		return repo.findAll();
	}
}
