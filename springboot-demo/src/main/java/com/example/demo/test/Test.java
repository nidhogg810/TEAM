package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.utils.MapUtils;

import java.lang.reflect.Array;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        TestData data = new TestData();
        data.setField1(1);
        TestData2 data2 =new TestData2();
        data2.setField2(2);
        data2.setField3("3");
        data.setTestData2(data2);


        System.out.println(MapUtils.objectToMap(null));
        System.out.println(MapUtils.objectToMap(1));
        byte b = 1;
        System.out.println(MapUtils.objectToMap(b));
        System.out.println(MapUtils.objectToMap('c'));
        System.out.println(MapUtils.objectToMap("str"));
        System.out.println(MapUtils.objectToMap(new HashMap<String,String>()));
        System.out.println(MapUtils.objectToMap(new ArrayList<String>()));
        Map<String,Object> map = new HashMap<>();
        map.put("key",data2);
        System.out.println(MapUtils.objectToMap(map));
        String str = "java.util.HashMap:{\"key\":{\"field3\":\"3\",\"field2\":2}}";
        String[] arr = str.split(":",2);
        System.out.println(arr.length);

        Template<TestData2> template = new Template<>();
        template.name = "find";
        template.data = new TestData2();
        template.data.setField2(2);
        System.out.println(template.getClass());
        String jsonstr = JSON.toJSONString(template);
        System.out.println(jsonstr);
        System.out.println(JSONObject.parseObject(jsonstr,Map.class));
        Template<TestData> t = new Template<>();
        t = JSON.parseObject(jsonstr,Template.class);
        System.out.println(t.data);
    }
}
