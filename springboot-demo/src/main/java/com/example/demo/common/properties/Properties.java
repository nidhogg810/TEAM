package com.example.demo.common.properties;

import com.example.demo.common.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;

@DependsOn("springContextUtils")
@Component
@Slf4j
public class Properties {

    private static String SQL = "select a.paramname,a.paramvalue from PARAM a";
    public Properties(){
        ApplicationContext ctx = SpringContextUtils.getApplicationContext();
        Environment env = ctx.getEnvironment();
        String DB_DRIVER = env.getProperty("spring.datasource.db1.driver-class-name");
        log.info("Properties#DB_DRIVER:"+DB_DRIVER);
        String DB_URL = env.getProperty("spring.datasource.db1.jdbc-url");
        String DB_USERNAME = env.getProperty("spring.datasource.db1.username");
        String DB_PASSWORD = env.getProperty("spring.datasource.db1.password");
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        try{
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            sm = conn.createStatement();
            rs = sm.executeQuery(SQL);
            while (rs.next()){
                String paramname = rs.getString(1);
                String paramvalue = rs.getString(2);
                System.setProperty(paramname,paramvalue);
            }
            log.info("System.properties:"+System.getProperties().toString());
        }catch (ClassNotFoundException e){
            log.error("DB driver error!",e);
        }catch(SQLException e){
            log.error("SQL error!",e);
        }
    }
}
