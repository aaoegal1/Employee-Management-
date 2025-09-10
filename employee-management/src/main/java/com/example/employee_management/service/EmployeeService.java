package com.example.employee_management.service;


import com.example.employee_management.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    void deleteEmployee(Long id);
    List<Employee> searchEmployees(String keyword);
    List<Employee> filterEmployeesByDepartment(Long departmentId);
    List<Employee> filterEmployeesBySalaryRange(Double minSalary, Double maxSalary);
}