package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private int emp_no;
    private LocalDate birth_date;
    private String full_name;
    private String gender;
    private LocalDate hire_date;
    private String title;
    private int salary;
}
