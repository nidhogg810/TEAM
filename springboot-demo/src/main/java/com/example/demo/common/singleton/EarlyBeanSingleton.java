package com.example.demo.common.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EarlyBeanSingleton {
    private final Map<String,Object> earlyBeanMap = new ConcurrentHashMap<>();
    private EarlyBeanSingleton(){}
    private static final EarlyBeanSingleton INSTANCE = EarlyBeanSingletonFactory.INSTANCE;

    public static EarlyBeanSingleton getInstance() {
        return INSTANCE;
    }
    private static class EarlyBeanSingletonFactory{
        static final EarlyBeanSingleton INSTANCE = new EarlyBeanSingleton();
    }

    public void put(String beanName,Object bean){
        earlyBeanMap.put(beanName, bean);
    }

    public Object get(String beanName){
        return earlyBeanMap.get(beanName);
    }
}
