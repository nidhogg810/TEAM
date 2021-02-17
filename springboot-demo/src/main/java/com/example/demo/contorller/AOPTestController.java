package com.example.demo.contorller;

import com.example.demo.common.annotation.LogParam;
import com.example.demo.service.AopTestServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AOPTestController {
    @Autowired
    AopTestServ aopTestServ;
    @RequestMapping("aoptest")
//    @LogParam(value = "aoptest")
    public String test(@RequestParam(value = "s") String s){
        Map<String,Object> map = new HashMap<>();
        map.put("test","ssst");
        Map<String,Object> resultmap = aopTestServ.aspectTest(map);
        return resultmap.toString();
    }
}
