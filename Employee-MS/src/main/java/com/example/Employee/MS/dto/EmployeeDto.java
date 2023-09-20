package com.example.Employee.MS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
    private int empID;
    private String empName;
    private String empAddress;
    private String empNumber;
}
