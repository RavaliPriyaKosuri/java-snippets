package com.example.seperate_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.seperate_db.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
