package com.example.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Bean(initMethod = "init",destroyMethod = "destroybean")
    public TestBean testBean(){
        return new TestBean();
    }
}
