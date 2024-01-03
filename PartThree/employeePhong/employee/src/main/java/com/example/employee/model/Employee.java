package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
    private Integer emp_no;
    private String first_name;
    private String last_name;
    private LocalDate birth_date;
    private LocalDate hire_date;
    private String gender;

    public Employee(Integer emp_no, String first_name, String last_name, Date birth_date, Date hire_date, String gender) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date.toLocalDate();
        this.hire_date = hire_date.toLocalDate();
        this.gender = gender;
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
    }
}
