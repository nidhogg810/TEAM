package com.example.demo.TestPostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControl {
    @Autowired
    Bean2 bean2;


    @RequestMapping("postconstruct/test")
    public void test(){
        bean2.execute();
    }
}
