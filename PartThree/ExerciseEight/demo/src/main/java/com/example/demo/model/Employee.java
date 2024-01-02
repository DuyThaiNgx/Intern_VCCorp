package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer empNo;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private Date hireDate;


}
