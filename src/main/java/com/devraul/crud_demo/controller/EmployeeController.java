package com.devraul.crud_demo.controller;

import com.devraul.crud_demo.dao.EmployeeDAO;
import com.devraul.crud_demo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeDAO employeeDAO;

    // i'll refactor this later, i'm just testing some examples (USE CONSTRUCTOR INJECTION)
    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    // expose "/employees" and return a list of all employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

}
