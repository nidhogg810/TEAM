package com.example.demo.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    public static <T> T map2Object(Map<String,Object> map,Class<T> classz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IntrospectionException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.convertValue(map, classz);

        T obj = classz.getDeclaredConstructor().newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(classz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method setter = property.getWriteMethod();
            if(setter != null){
                setter.invoke(obj,map.get(key));
            }
        }
        return obj;
    }
}
