package com.example.demo.TestPostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class PostConstructConfig {
    private Bean1 bean1;
    @PostConstruct
    public void init(){
        log.info("execute postconstruct init!");
        bean1 = new Bean1();
    }

    public Bean1 getBean1(){
        return bean1;
    }
}
