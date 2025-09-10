package com.example.employee_management.controller;

import com.example.employee_management.model.Department;
import com.example.employee_management.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments/list";
    }

    @GetMapping("/new")
    public String showDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/form";
    }

    @PostMapping("/save")
    public String saveDepartment(@Valid @ModelAttribute("department") Department department,
                                 BindingResult result, Model model) {

        // Check for duplicate department name
        if (department.getId() == null) {
            if (departmentService.isDepartmentExists(department.getName())) {
                result.rejectValue("name", "duplicate", "A department with this name already exists");
            }
        } else {
            if (departmentService.isDepartmentExists(department.getName(), department.getId())) {
                result.rejectValue("name", "duplicate", "A department with this name already exists");
            }
        }

        if (result.hasErrors()) {
            return "departments/form";
        }

        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isPresent()) {
            model.addAttribute("department", department.get());
            return "departments/form";
        } else {
            return "redirect:/departments";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}