package com.example.demo.dto;

import com.example.demo.model.DepartmentSalary;
import com.example.demo.model.EmployeeSalary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalaryDto {
    private List<EmployeeSalary> employeeSalaryList;
    private List<DepartmentSalary> departmentSalaryList;
    private long companySalary;
}