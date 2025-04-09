package com.hrms.dept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrms.dept.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // You can add custom query methods here if needed.
}
