package com.example.employee_management.service;

import com.example.employee_management.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentById(Long id);
    Department saveDepartment(Department department);
    void deleteDepartment(Long id);
    boolean isDepartmentExists(String name);
    boolean isDepartmentExists(String name, Long excludeId);
}