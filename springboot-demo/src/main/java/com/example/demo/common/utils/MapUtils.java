package com.example.demo.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.constants.SystemConstants;
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
    /**使用jackson
     * 如果obj为null，则同样返回null
     * 将简单的POJO转化为JSON字符串，
     * key的名字为对应的property名
     * 注意如果没有public的get方法，则没有对应map字段
     * @param obj 需要转化的POJO对象
     * @return 转化结果的JSON字符串
     * @throws Exception
     */
    public static Object objectToMap(Object obj) throws Exception {
        if(obj == null) {
            return null;
        }
        StringBuffer resultJsonStr = new StringBuffer();
        resultJsonStr.append(obj.getClass().getName());
        resultJsonStr.append(":");
        ObjectMapper mapper = new ObjectMapper();
        resultJsonStr.append(mapper.writeValueAsString(obj));
        return resultJsonStr.toString();
    }

    /**
     * 将从缓存中获得的内容，反序列化为POJO
     * @param value 返回的value类型可能为：1、null；2、基本数据类型以及String；3、map类型
     * @return value==null，返回null；value为基本数据类型以及String，返回value本身；3、map类型转化为POJO
     */
    public static Object parse(Object value) throws ClassNotFoundException, IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if(value == null){
            return null;
        }
        if(isPrimitive(value)){
            return value;
        }
        Map<String,Object> valMap = (Map<String, Object>) value;
        Class clazz = Class.forName(valMap.get(SystemConstants.SERIALIZE_MAP_CLASSNAME).toString());
        return map2Object(valMap,clazz);
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

    /**
     * 判断类型是否是java基本数据类型、基本数据类型包装类、String其中的一种
     * @param clazz
     * @return
     */
    public static boolean isPrimitive(Class clazz) {
        if(clazz.isPrimitive()||clazz == String.class){
            return true;
        }
        try {
            return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (NoSuchFieldException exception){
            log.warn("",exception);
            return false;
        } catch (Exception e) {
            log.debug("com.example.demo.common.utils.MapUtils#isPrimitive(Class) Error!",e);
            return false;
        }
    }
    /**
     * 判断类型是否是java基本数据类型、基本数据类型包装类、String其中的一种
     * @param obj
     * @return
     */
    public static boolean isPrimitive(Object obj) {
        if(obj instanceof String){
            return true;
        }
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (NoSuchFieldException exception){
            log.warn("",exception);
            return false;
        } catch (Exception e) {
            log.debug("com.example.demo.common.utils.MapUtils#isPrimitive(Object) Error!",e);
            return false;
        }
    }
}
