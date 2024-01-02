package com.example.exerciseseven.controller;

import com.example.exerciseseven.model.Employees;
import com.example.exerciseseven.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping ("/employee")
    public List<Employees> getEmployeesWithFilters(
            @RequestParam(name = "hire_date_from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate,
            @RequestParam(name = "salary", required = false) Float salary,
            @RequestParam(name = "dept_no", required = false) String deptNo,
            @RequestParam(name = "title", required = false) String title) {

        return employeeRepository.findEmployeesByCustomQuery(hireDate, salary, deptNo, title);
    }
}
