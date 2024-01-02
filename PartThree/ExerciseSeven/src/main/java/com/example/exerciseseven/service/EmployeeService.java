package com.example.exerciseseven.service;

import com.example.exerciseseven.model.Employees;
import com.example.exerciseseven.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employees> getEmployeesWithFilters(Date hireDate, Float salary, String deptNo, String title) {
        return employeeRepository.findEmployeesByCustomQuery(hireDate, salary, deptNo, title);
    }
}