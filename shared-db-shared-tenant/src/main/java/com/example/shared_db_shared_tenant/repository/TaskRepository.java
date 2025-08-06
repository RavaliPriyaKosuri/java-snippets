package com.example.shared_db_shared_tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.shared_db_shared_tenant.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query(value = "SELECT * FROM tasks where tenant_id = ?1", nativeQuery = true)
	List<Task> findAllByTenantId(String tenantId);
}
