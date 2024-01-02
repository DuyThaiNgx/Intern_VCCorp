package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalary {
    private int empNo;
    private long salary;

//    public EmployeeSalary(int empNo, int salary) {
//        this.empNo = empNo;
//        this.salary = salary;
//    }
}
