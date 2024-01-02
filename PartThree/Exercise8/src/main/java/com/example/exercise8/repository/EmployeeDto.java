package main.java.com.example.exercise8.dto;

import main.java.com.example.exercise8.model.Employee;

import java.util.List;

public interface EmployeeDto {
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
}
