package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentSalary {
    private String dept_no;
    private long salary;

//    public DepartmentSalary(String dept_no, long salary) {
//        this.dept_no = dept_no;
//        this.salary = salary;
//    }
}
