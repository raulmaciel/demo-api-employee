package com.devraul.crud_demo.controller;

import com.devraul.crud_demo.dao.EmployeeDAO;
import com.devraul.crud_demo.entity.Employee;
import com.devraul.crud_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    // i'll refactor this later, i'm just testing some examples (USE CONSTRUCTOR INJECTION)
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // expose "/employees" and return a list of all employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

}
