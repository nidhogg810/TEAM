package com.example.demo.TestPostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bean2 {
    private Bean1 bean1;

    public void execute(){
        log.info("{} execute",Bean2.class.getName());
    }
}
