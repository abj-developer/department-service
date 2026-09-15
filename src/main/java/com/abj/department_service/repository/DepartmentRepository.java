package com.abj.department_service.repository;

import com.abj.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentId(Long departmentId);

    boolean existsByDepartmentCode(String departmentCode);
}
