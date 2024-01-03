package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.SalaryDTO;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(
            @RequestParam(required = false, value = "hire_date_from") String hireDateFrom,
            @RequestParam(required = false, value = "salary") Integer salary,
            @RequestParam(required = false, value = "dept_no") String deptNo,
            @RequestParam(required = false, value = "title") String title) {
        return employeeService.getEmployees(hireDateFrom, salary, deptNo, title);
    }

    @PostMapping("/employees/setup")
    public Employee setupEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        return employeeService.setupEmployee(employeeDTO.getEmp_no(), employeeDTO.getBirth_date().toString(),
                employeeDTO.getFull_name(), employeeDTO.getGender(), employeeDTO.getHire_date().toString(),
                employeeDTO.getTitle(), employeeDTO.getSalary());
    }

    @GetMapping("/employees/transfer")
    public void transferEmployee(
            @RequestParam(required = true, value = "emp_no") int emp_no,
            @RequestParam(required = true, value = "dept_no") String dept_no,
            @RequestParam(required = true, value = "title") String title){
        employeeService.transferEmployee(emp_no, dept_no, title);
    }

    @GetMapping("/employees/salary")
    public SalaryDTO getSalary(@RequestParam(required = true, value = "date") String date){
        return employeeService.getSalary(date);
    }
}
