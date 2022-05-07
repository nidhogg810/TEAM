package com.example.demo.common.utils;

public final class StringUtils {
    public static String toLowerCaseFirstOne(String name){
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
