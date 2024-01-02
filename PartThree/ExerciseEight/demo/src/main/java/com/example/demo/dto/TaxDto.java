package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxDto {
    private int empNo;
    private int tax;
    private int month;
    private int year;

    public TaxDto(int empNo, int tax, int month, int year) {
        this.empNo = empNo;
        this.tax = tax;
        this.month = month;
        this.year = year;
    }
}