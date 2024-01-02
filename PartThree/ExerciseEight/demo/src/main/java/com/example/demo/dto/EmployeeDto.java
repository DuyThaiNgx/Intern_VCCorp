package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int emp_no;
    private String full_name;
    private String gender;
    private String birth_date;
    private String hire_date;
    private String title;
    private int salary;
}
