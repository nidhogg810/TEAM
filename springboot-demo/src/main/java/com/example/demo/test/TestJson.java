package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class TestJson {
    public static void main(String[] args) throws ClassNotFoundException {
        TestData data = new TestData();
        data.setField1(1);
        String jsonString = JSON.toJSONString(data, SerializerFeature.WriteClassName);
        System.out.println(jsonString);
        JSONObject jo = JSONObject.parseObject(jsonString);
        String classname = (String) jo.get("@type");
        Class clazz = Class.forName(classname);
        Object bean = JSONObject.parseObject(jsonString,clazz);
        System.out.println(bean);
    }
}
