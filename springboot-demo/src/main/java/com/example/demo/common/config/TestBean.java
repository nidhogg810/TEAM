package com.example.demo.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
/**2022-02-21 09:41:45.193  INFO 3601 --- [           main] com.example.demo.common.config.TestBean  : testbean construction
 2022-02-21 09:41:45.194  INFO 3601 --- [           main] com.example.demo.common.config.TestBean  : testbean applicaitoncontext aware
 2022-02-21 09:41:45.194  INFO 3601 --- [           main] com.example.demo.common.config.TestBean  : testbean postConstruct
 2022-02-21 09:41:45.194  INFO 3601 --- [           main] com.example.demo.common.config.TestBean  : testbean initializing bean
 2022-02-21 09:41:45.194  INFO 3601 --- [           main] com.example.demo.common.config.TestBean  : testbean init
 2022-03-23 15:22:34.416 [main] INFO  com.example.demo.common.config.TestBean - testbean postProcessBeforeInitialization bean
 2022-03-23 15:22:34.417 [main] INFO  com.example.demo.common.config.TestBean - testbean postProcessAfterInitialization bean
 */
@Slf4j
public class TestBean implements InitializingBean, DisposableBean, ApplicationContextAware, BeanPostProcessor {

    public TestBean(){
       log.info("testbean construction");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
        log.info("testbean postProcessBeforeInitialization bean");
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
        log.info("testbean postProcessAfterInitialization bean");
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("testbean initializing bean");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("testbean applicaitoncontext aware");
    }

    public void init(){
        log.info("testbean init");
    }

    public void destroybean(){
        log.info("testbean destroybean");
    }

    @Override
    public void destroy() throws Exception {
        log.info("testbean disposableBean");
    }
    @PostConstruct
    public void postConstruct(){
        log.info("testbean postConstruct");
    }
    @PreDestroy
    public void preDestory(){
        log.info("testbean preDestory");
    }
}
