package com.example.demo.contorller;

import com.example.demo.common.utils.xml.XmlParseHelper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("xml")
public class XmlController {
    @RequestMapping("parse")
    public String ParseXml() throws JAXBException {
//        return XmlParseHelper.JAXBParseXml(JAXBDataRootConfigs.class,
//                "/Users/yuhan/TEAM/mySpringboot/springboot-demo/src/main/resources/test.xml").toString();
        return "";
    }
}
