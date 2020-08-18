package com.example.demo.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("jdbcSelect")
    public String jdbcSelect(){
        String sql = "select e.emp_no from employees e order by e.emp_no asc";
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList.toString();
    }

}
