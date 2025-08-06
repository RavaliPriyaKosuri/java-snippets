package com.profile.profileDemoApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profile.profileDemoApplication.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
