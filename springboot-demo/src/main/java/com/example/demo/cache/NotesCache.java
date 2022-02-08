package com.example.demo.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuhan
 */
@Slf4j
public final class NotesCache {

    private static NotesCache INSTANCE;
    private static Map<Object, ConcurrentHashMap<Object,Object>> CACHE = null;

    private NotesCache(){
        CACHE = new ConcurrentHashMap<>(10);
    }

    public static NotesCache getInstance(){
        if(INSTANCE==null){
            INSTANCE = new NotesCache();
        }
        return INSTANCE;
    }

    public Object get(Object name,Object key){
        ConcurrentHashMap<Object,Object> nameHashMap = CACHE.get(name);
        if(Objects.isNull(nameHashMap)){
            return null;
        }
        return nameHashMap.get(key);
    }
    public void put(Object name,Object key,Object value){
        ConcurrentHashMap<Object,Object> nameHashMap = CACHE.get(name);
        if(Objects.isNull(nameHashMap)){
            nameHashMap = new ConcurrentHashMap<>(10);
            CACHE.put(name,nameHashMap);
        }
        nameHashMap.put(key, value);
    }

}
