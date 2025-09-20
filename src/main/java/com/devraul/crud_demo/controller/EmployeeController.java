package com.devraul.crud_demo.controller;

import com.devraul.crud_demo.dao.EmployeeDAO;
import com.devraul.crud_demo.entity.Employee;
import com.devraul.crud_demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }


    // expose "/employees" and return a list of all employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable Long id){
        Employee employee = employeeService.findById(id);

        if(employee == null){
            throw new RuntimeException("Employee id not found - " + id);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(null);
        Employee newEmployee = employeeService.save(employee);
        return newEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @PatchMapping("/employees/{id}")
    public Employee partialUpdateEmployee(@PathVariable Long id, @RequestBody Map<String, Object> pathPayload){
        Employee employee = employeeService.findById(id);
        if (employee == null){
            throw new RuntimeException("Id not founded - " + id);
        }
        if (pathPayload.containsKey("id")){
            throw new RuntimeException("You can't modify the id. ");
        }

        Employee patchedEmployee = apply(pathPayload, employee);
        Employee dbEmployee = employeeService.save(patchedEmployee);
        return dbEmployee;

    }


    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteById(id);
    }

    public Employee apply(Map<String, Object> patchPayload, Employee tempEmployee){

        //Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        //Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //Apply or merge the patch
        employeeNode.setAll(patchNode);

        //Convert back JSON object to Employee object
        return objectMapper.convertValue(employeeNode, Employee.class);

    }

}
