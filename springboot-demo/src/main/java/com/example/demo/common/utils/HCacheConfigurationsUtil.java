package com.example.demo.common.utils;

import com.example.demo.common.config.NotesCacheConfiguration;
import com.example.demo.common.constants.HCacheType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class HCacheConfigurationsUtil {
    private static final Map<HCacheType,Class<?>> MAPPINGS;
    static {
        Map<HCacheType,Class<?>> mappins = new EnumMap<>(HCacheType.class);
        mappins.put(HCacheType.NOTES, NotesCacheConfiguration.class);
        mappins.put(HCacheType.NONE,null);
        MAPPINGS = Collections.unmodifiableMap(mappins);
    }

    private HCacheConfigurationsUtil(){
    }

    public static String getConfiguration(HCacheType type){
        Class<?> configuration = MAPPINGS.get(type);
        if(type == null || HCacheType.NONE == type){
            return null;
        }
        return configuration.getName();
    }
}
