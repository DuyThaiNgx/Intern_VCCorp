package com.example.exerciseseven.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    private int emp_no;
    private Date hire_date;
    private String first_name;
    private String last_name;
    private float salary;
    private String dept_no;
    private String title;
}
