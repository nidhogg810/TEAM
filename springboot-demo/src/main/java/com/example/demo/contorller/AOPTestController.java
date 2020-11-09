package com.example.demo.contorller;

import com.example.demo.common.annotation.LogParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class AOPTestController {
    @RequestMapping("aoptest")
    @LogParam
    public String test(@RequestParam(value = "s") String s){
        return s+"success";
    }
}
