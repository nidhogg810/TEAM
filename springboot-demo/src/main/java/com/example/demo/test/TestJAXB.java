package com.example.demo.test;

import com.example.demo.common.utils.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Map;


public class TestJAXB {
    public static void main(String[] args) throws Exception {
//        File file =  new File("/Users/yuhan/TEAM/mySpringboot/springboot-demo/src/main/resources/test.xml");
//        JAXBContext context = JAXBContext.newInstance(JAXBDataRootConfigs.class);
//
//        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
//        JAXBDataRootConfigs configs = (JAXBDataRootConfigs) jaxbUnmarshaller.unmarshal(file);
//        System.out.println(configs);
        String filepath = "/Users/yuhan/TEAM/mySpringboot/springboot-demo/src/main/resources/application.yml";
        Map<String,Object> obj = FileUtils.loadYamlFile(filepath);
        System.out.println(obj);
    }
}
