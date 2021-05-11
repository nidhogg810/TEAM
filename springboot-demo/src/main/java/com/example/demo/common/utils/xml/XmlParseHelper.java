package com.example.demo.common.utils.xml;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParseHelper {
    public static <T> T JAXBParseXml(Class<T> clazz,String filepath) throws JAXBException {
       return JAXBParseXml(clazz,new File(filepath));
    }
    public static <T> T JAXBParseXml(Class<T> clazz,File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(file);
    }
}
