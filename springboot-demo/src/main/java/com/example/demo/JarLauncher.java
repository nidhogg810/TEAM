package com.example.demo;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JarLauncher {

    private static final Logger log = LoggerFactory.getLogger(MainClass.class);
    @SneakyThrows
    public static void main(String[] args) {
        List<URL> urls = new ArrayList<>(50);
        URL rootUrl = JarLauncher.class.getResource("/");
        urls.add(rootUrl);
        CustomClassLoader classLoader = new CustomClassLoader(false, null, urls.toArray(new URL[0]), JarLauncher.class.getClassLoader());
        Thread.currentThread().setContextClassLoader(classLoader);
        try {
            Class<?> mainClass = Class.forName("com.example.demo.MainClass",false,Thread.currentThread().getContextClassLoader());
            Method mainMethod = mainClass.getMethod("main", String[].class);
            log.info(mainClass.getClassLoader().toString());
            mainMethod.setAccessible(true);
            mainMethod.invoke(null,  new Object[] { args });
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
