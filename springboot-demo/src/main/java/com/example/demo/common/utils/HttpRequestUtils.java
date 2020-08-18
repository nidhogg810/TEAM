package com.example.demo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class HttpRequestUtils {
    private static final String LOCAL_HOST = new String("127.0.0.1");
    public static String getRequestIp(HttpServletRequest request){
        String ipAddress = "";
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if(StringUtils.isBlank(ipAddress)||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if(StringUtils.isBlank(ipAddress)||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if(StringUtils.isBlank(ipAddress)||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getRemoteAddr();
                if (LOCAL_HOST.equals(ipAddress)){
                    //根据网卡抽取本机ip
                    InetAddress inetAddress = null;
                    try {
                        inetAddress = InetAddress.getLocalHost();
                    }catch (UnknownHostException e){
                        log.error("UnknownHostException",e);
                    }
                    ipAddress = inetAddress.getHostAddress();
                }
                //对通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
                if (ipAddress != null && ipAddress.length()>15){
                    if (ipAddress.indexOf(",")>0){
                        ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
                    }
                }
            }
        }catch (Exception e){
            log.error("get request ip error!",e);
            ipAddress = "";
        }
        return ipAddress;
    }
}
