package com.example.demo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class HttpHostUtils {
    @Autowired
    private Environment environment;

    public String getLocalHostIP(){
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        }catch (UnknownHostException e){
            log.error("UnknownHostException",e);
        }
        return localhost.getHostAddress();
    }

    public String getLocalHostPORT(){
        return environment.getProperty("local.server.port");
    }
}
