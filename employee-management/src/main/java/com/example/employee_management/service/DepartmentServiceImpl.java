package com.example.employee_management.service;

import com.example.employee_management.model.Department;
import com.example.employee_management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public boolean isDepartmentExists(String name) {
        // Check if a department with the given name already exists
        return getAllDepartments().stream()
                .anyMatch(dept -> dept.getName().equalsIgnoreCase(name));
    }

    @Override
    public boolean isDepartmentExists(String name, Long excludeId) {
        // Check if a department with the given name already exists, excluding a specific ID
        return getAllDepartments().stream()
                .filter(dept -> !dept.getId().equals(excludeId))
                .anyMatch(dept -> dept.getName().equalsIgnoreCase(name));
    }
}