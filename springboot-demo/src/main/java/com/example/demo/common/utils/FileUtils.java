package com.example.demo.common.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author yuhan
 */
public class FileUtils {
    public static Map<String,Object> loadYamlFile(String filepath) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Object yamlParse = yaml.load(new FileInputStream(filepath));
        return (Map<String, Object>) yamlParse;
    }
}
