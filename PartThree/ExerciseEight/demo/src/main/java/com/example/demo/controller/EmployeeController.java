package com.example.demo.controller;

import com.example.demo.dto.SalaryDto;
import com.example.demo.dto.TransferDto;
import com.example.demo.model.Employee;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping ("/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("addemployee")
    public void addEmployee(@ModelAttribute EmployeeDto employee) {
        employeeService.addEmployee(employee.getEmp_no(), employee.getFull_name(), employee.getGender(), employee.getBirth_date(), employee.getHire_date(),
                employee.getTitle(), employee.getSalary());
    }

//    @PostMapping("addemployee")
//    public void addEmployee(@ModelAttribute int emp_no, String full_name, String gender, String birth_date, String hire_date,
//                            String title, int salary) {
//        employeeService.addEmployee(emp_no, full_name, gender, birth_date, hire_date,
//                title, salary);
//    }
    @PutMapping("/transferEmployee")
    public ResponseEntity<?> transferEmployee(@ModelAttribute TransferDto transferDto){
        employeeService.transferEmployee(transferDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getSalary")
    public ResponseEntity<?> getSalary(@RequestParam String date){
        SalaryDto salaryDto = employeeService.getSalary(String.valueOf(date));
        return ResponseEntity.ok(salaryDto);
    }
}