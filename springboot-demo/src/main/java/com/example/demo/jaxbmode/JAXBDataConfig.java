package com.example.demo.jaxbmode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "localCacheConfig")
public class JAXBDataConfig {
    @XmlAttribute(name = "cacheName")
    private String cacheName;

    private List<JAXBDataProperty> properties;
    @XmlElement(name = "property")
    public List<JAXBDataProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<JAXBDataProperty> properties) {
        this.properties = properties;
    }
}
