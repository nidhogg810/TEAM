package com.example.demo.contorller;

import com.example.demo.common.utils.CrontabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContabTestController {
    @Autowired
    private CrontabUtil crontabUtil;
    @RequestMapping("cron")
    public String parseCrontab(){
        String cron = "0 0/5 * * * ?";
        return crontabUtil.parseCon(cron).toString();
    }
}
