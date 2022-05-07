package com.example.demo.launch;

import com.example.demo.common.singleton.ClassDefinedSingleton;
import com.example.demo.common.singleton.EarlyBeanSingleton;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanScanLauncher {
    private static final Logger log = LoggerFactory.getLogger(BeanScanLauncher.class);
    public static Map<String,Class<?>> findBeanClasses(){
        Map<String,Class<?>> beanClasses = new ConcurrentHashMap<>();
        Map<String,Class<?>> map = ClassDefinedSingleton.getInstance().getDefinedClassMap();
        map.forEach((k,v)->{
            if(v.getDeclaredAnnotation(Configuration.class)!=null){
                Method[] methods = v.getMethods();
                Arrays.asList(methods).forEach(method -> {
                    Bean beanAnno = method.getDeclaredAnnotation(Bean.class);
                    if(beanAnno!=null){
                        String beanName = beanAnno.name().length != 0 ? beanAnno.name()[0]:
                                com.example.demo.common.utils.StringUtils.toLowerCaseFirstOne(method.getName());
                        beanClasses.put(beanName,method.getReturnType());
                    }
                });
            }
            Component componentAnno = v.getDeclaredAnnotation(Component.class);
            if(componentAnno!=null){
                String beanName = StringUtils.isNotBlank(componentAnno.value())? componentAnno.value():
                        com.example.demo.common.utils.StringUtils.toLowerCaseFirstOne(v.getSimpleName());
                beanClasses.put(beanName,v);
            }
        });
        return beanClasses;
    }

    public static void doCreateEarlyBean(Map<String,Class<?>> beanClasses){
        beanClasses.forEach((k,v)->{
            Constructor constructor = null;
            Object bean = null;
            try {
                log.info("do create beanName[{}],Class[{}]",k,v);
                if(!v.isInterface()) {
                    constructor = v.getConstructor();
                    bean = constructor.newInstance();
                    EarlyBeanSingleton.getInstance().put(k,bean);
                }else {
                    log.error("beanName:{},class:{} ,do not support interface now",k,v);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                     InstantiationException e) {
                log.error("beanName:{},class:{}",k,v);
                throw new RuntimeException(e);
            }
        });
    }
}
