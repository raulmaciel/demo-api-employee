package com.devraul.crud_demo.dao;

import com.devraul.crud_demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
