package com.example.demo.service;

import com.example.demo.common.annotation.LogParam;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class AopTestServ {
    @LogParam("ssss")
    public Map<String,Object> aspectTest(Map<String,Object> map){
        return map;
    }
}
