package com.example.schema_per_tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.schema_per_tenant.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
