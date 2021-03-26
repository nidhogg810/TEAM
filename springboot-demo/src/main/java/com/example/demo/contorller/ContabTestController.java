package com.example.demo.contorller;

import com.example.demo.common.utils.CrontabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ContabTestController {
    @Autowired
    private CrontabUtil crontabUtil;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("cron")
    public String parseCrontab(){
//        String cron = "0 0/5 * * * ?";
//        return crontabUtil.parseCon(cron).toString();
        Date date = new Date();
        return sdf.format(date);

    }
}
