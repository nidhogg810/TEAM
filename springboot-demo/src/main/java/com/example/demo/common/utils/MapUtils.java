package com.example.demo.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class MapUtils {
    /**
     * 将简单的POJO转化为map，
     * key的名字为对应的property名
     * 注意如果没有public的get方法，则没有对应map字段
     * @param obj 需要转化的POJO对象
     * @return 转化结果的Map对象
     * @throws Exception
     */
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
            if(!isPrimitive(value)){
                value = objectToMap(value);
            }
            map.put(key, value);
        }

        return map;
    }

    /**
     * 将map转化为指定类的对象，
     * key的名字为对应的property名
     * 注意如果对应字段没有public的set方法则对应属性为默认值
     * @param map 需要转化的Map对象
     * @param classz 目标的POJO的class
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     */
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
            if(setter != null&&isPrimitive(property.getPropertyType())){
                setter.invoke(obj,map.get(key));
            }else if(setter!=null){
                setter.invoke(obj,map2Object((Map<String, Object>) map.get(key),property.getPropertyType()));
            }
        }
        return obj;
    }

    /**
     * 根据指定的XXX.XXX.XXX递归的访问嵌套map的指定value
     * @param map
     * @param key
     * @return
     */
    public static Object get(Map<String,Object> map, String key){
        return get(map,key,"\\.");
    }


    /**
     * 根据指定的XXX.XXX.XXX递归的访问嵌套map的指定value,key path的分割符可以任意指定
     * @param map
     * @param key
     * @param splitChar 分割符

     * @return
     */
    public static Object get(final Map<String,Object> map, String key, String splitChar){
        Object result = null;
        String[] keys = key.split(splitChar);
        Map<String,Object> tmp = map;
        for(int i=0; i<keys.length; i++){
            Object nowValue = tmp.get(keys[i]);
            if(i != keys.length-1){
                if(nowValue instanceof Map){
                    tmp = (Map<String, Object>) nowValue;
                    continue;
                }else {
                    log.error("input key path [{}] error! cannot find key[{}] in Map[{}]",key,keys[i],map);
                    throw new IllegalArgumentException("input key path ["+key+"] error! cannot find key["+keys[i]+"] in Map["+map+"]");
                }
            }else {
                if (nowValue == null) {
                    log.error("input key path [{}] error! cannot find key[{}] in Map[{}] or value is null",key,keys[i],map);
                    throw new IllegalArgumentException("input key path ["+key+"] error! cannot find key["+keys[i]+"] in Map["+map+"]");
                }else {
                    result = nowValue;
                }
            }

        }
        return result;
    }

    public static boolean isPrimitive(Class clazz) throws NoSuchMethodException {
        if(clazz.isPrimitive()||clazz == String.class){
            return true;
        }
        try {
            return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPrimitive(Object obj) {
        if(obj instanceof String){
            return true;
        }
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
