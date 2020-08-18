package com.example.demo.common.model;

import lombok.Data;

import java.sql.Date;

@Data
public class EmployeesModel {
    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;

}
