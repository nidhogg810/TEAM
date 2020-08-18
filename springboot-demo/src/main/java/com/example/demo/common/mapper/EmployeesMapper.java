package com.example.demo.common.mapper;

import com.example.demo.common.model.EmployeesModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

@Mapper
public interface EmployeesMapper {
    @Select("select emp_no,birth_date,first_name,last_name,gender,hire_date from employees e")
    @Results(id="employeeMap",value = {
            @Result(property = "empNo",column = "emp_no",javaType = int.class),
            @Result(property = "birthDate",column = "birth_date",javaType = Date.class),
            @Result(property = "firstName",column = "first_name",javaType = String.class),
            @Result(property = "lastName",column = "last_name",javaType = String.class),
            @Result(property = "gender",column = "gender",javaType = String.class),
            @Result(property = "hireDate",column = "hire_date",javaType = Date.class)
    })
    public List<EmployeesModel> selectAll();

//    @Select("select emp_no,birth_date,first_name,last_name,gender,hire_date from employees e where e.emp_no = #{empNo}")
//    @ResultMap(value = "employeeMap")
//    public EmployeesModel getOneEmployee(@Param("empNo") String empNo);

    @Select("select count(1) as cnt from employees")
    public int countEmployees();
    //分页查询
    @Select("select emp_no,birth_date,first_name,last_name,gender,hire_date from employees e")
    @ResultMap(value = "employeeMap")
    public List<EmployeesModel> selectPage();
}
