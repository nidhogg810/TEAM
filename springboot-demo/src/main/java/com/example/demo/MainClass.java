package com.example.demo;

import com.example.demo.common.singleton.ClassDefinedSingleton;
import com.example.demo.common.singleton.EarlyBeanSingleton;
import com.example.demo.launch.BeanScanLauncher;
import com.example.demo.thread.BeanClassThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.beans.beancontext.BeanContextMembershipEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainClass {
    private static final Logger log = LoggerFactory.getLogger(MainClass.class);
    public static void main(String[] args) {
        log.info("classLoader:"+MainClass.class.getClassLoader());
        log.info("Thread classLoader:"+Thread.currentThread().getContextClassLoader());
        ExecutorService poolExecutor = Executors.newSingleThreadExecutor();
        Future<List<String>> classScanListResult = poolExecutor.submit(new BeanClassThread());
        try {
            List<String> classScanList =  classScanListResult.get();
            CountDownLatch latch = new CountDownLatch(classScanList.size());
            for(String name:classScanList){
                poolExecutor.submit(() -> {
                    try {
                        ClassDefinedSingleton.getInstance()
                                .getDefinedClassMap().put(name,
                                        Class.forName(name,false,Thread.currentThread().getContextClassLoader()));
                        latch.countDown();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            latch.await();
            Map<String,Class<?>> beanClasses = BeanScanLauncher.findBeanClasses();
            doCreate(beanClasses);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }finally {
            poolExecutor.shutdown();
        }

    }


    public static void doCreate(Map<String,Class<?>> beanClasses){
        BeanScanLauncher.doCreateEarlyBean(beanClasses);
        log.info(EarlyBeanSingleton.getInstance().toString());
    }


}
