package com.example.demo.common.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassDefinedSingleton {
    private final Map<String,Class<?>> definedClassMap;
    private ClassDefinedSingleton(Map<String,Class<?>> map){
        definedClassMap = map;
    }
    private static class ClassDefinedSingletonFactory{
        private static final ClassDefinedSingleton instance = new ClassDefinedSingleton(new ConcurrentHashMap<>());
    }

    public static ClassDefinedSingleton getInstance() {
        return ClassDefinedSingletonFactory.instance;
    }

    public Map<String,Class<?>> getDefinedClassMap(){
        return definedClassMap;
    }
}
