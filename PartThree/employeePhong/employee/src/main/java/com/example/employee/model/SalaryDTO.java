package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SalaryDTO {
    private List<EmployeeSalary> employeeSalaries;
    private List<DepartmentSalary> departmentSalaries;
    private long totalSalary;
}
