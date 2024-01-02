package com.example.demo.service;

import com.example.demo.dto.SalaryDto;
import com.example.demo.dto.TransferDto;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeDto;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }


    public void addEmployee(int emp_no, String full_name, String gender, String birth_date, String hire_date,
                            String title, int salary) {
        employeeRepository.addEmployee(emp_no, full_name, gender,birth_date,hire_date,title,salary);
    }

//    public void addEmployee(int empNo, String birthDate, String fullName, String gender, String hireDate,
//                            String title, int salary) {
//        employeeRepository.setupNewEmployee(Employee);
//    }

    public void transferEmployee(TransferDto transferDto) {
        employeeRepository.transferEmployee(transferDto.getEmpNo(), transferDto.getDeptNo(), transferDto.getTitle());
    }
    public SalaryDto getSalary(String date) {
        return employeeRepository.getSalary(date);
    }
}

