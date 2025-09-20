package com.devraul.crud_demo.repository;

import com.devraul.crud_demo.dao.EmployeeDAO;
import com.devraul.crud_demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository implements EmployeeDAO {
    //define field for entity manager
    private final EntityManager entityManager;

    //set up constructor injection

    @Autowired
    public EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create the query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        //execute the query
        List<Employee> resultList = query.getResultList();

        // return results
        return resultList;
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        return newEmployee;
    }

    @Override
    public void deleteById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
