package com.example.demo.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DbUsabilityMapper {

    @Select("select count(1) from employees")
    public String selectDual();
}
