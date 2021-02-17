package com.example.demo.TestPostConstruct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bean1 {
    public Bean1(){
        log.info("new Bean1!");
    }
    public void execute(){
        log.info("{} execute",Bean1.class.getName());
    }
}
