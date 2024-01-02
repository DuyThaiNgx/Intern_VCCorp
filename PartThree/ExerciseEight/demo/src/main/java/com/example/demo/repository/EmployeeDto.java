package com.example.demo.repository;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeDto {
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
}
