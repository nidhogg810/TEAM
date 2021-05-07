package com.example.demo.jaxbmode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "localCacheConfigs")
public class JAXBDataRootConfigs {

    private List<JAXBDataConfig> configs;
    @XmlElement(name = "localCacheConfig")
    public List<JAXBDataConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<JAXBDataConfig> configs) {
        this.configs = configs;
    }
}

