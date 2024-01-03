package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.SalaryDTO;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(String hireDateFrom, Integer salary, String deptNo, String title) {
        return employeeRepository.getEmployees(salary, deptNo, title, hireDateFrom);
    }

    public Employee setupEmployee(int emp_no, String birth_date, String full_name, String gender, String hire_date, String title, int salary){
        return employeeRepository.setupEmployee(emp_no, birth_date, full_name, gender, hire_date, title, salary);
    }

    public void transferEmployee(int employeeId, String newDepartment, String newTitle){
        employeeRepository.transferEmployee(employeeId, newDepartment, newTitle);
    }

    public SalaryDTO getSalary(String date){
        return employeeRepository.getSalary(date);
    }
}
