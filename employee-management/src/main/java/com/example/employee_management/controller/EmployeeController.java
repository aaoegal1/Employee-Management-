package com.example.employee_management.controller;



import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import com.example.employee_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listEmployees(Model model,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "departmentId", required = false) Long departmentId,
                                @RequestParam(value = "minSalary", required = false) Double minSalary,
                                @RequestParam(value = "maxSalary", required = false) Double maxSalary) {

        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("employees", employeeService.searchEmployees(keyword));
        } else if (departmentId != null) {
            model.addAttribute("employees", employeeService.filterEmployeesByDepartment(departmentId));
        } else if (minSalary != null && maxSalary != null) {
            model.addAttribute("employees", employeeService.filterEmployeesBySalaryRange(minSalary, maxSalary));
        } else {
            model.addAttribute("employees", employeeService.getAllEmployees());
        }

        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/list";
    }

    @GetMapping("/new")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employees/form";
        }

        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employees/form";
        } else {
            return "redirect:/employees";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}