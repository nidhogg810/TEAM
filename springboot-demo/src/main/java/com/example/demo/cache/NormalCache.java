package com.example.demo.cache;

import com.example.demo.common.constants.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
@Component
@Slf4j
public class NormalCache implements Cache {
    /**
     * 使用ConcurrentHashMap作为数据的存储
     */
    private static Map<String, Object> store = new ConcurrentHashMap<>();

    @Override
    public String getName() {
        return CacheConstants.CONCURRENTMAP_CACHE;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public ValueWrapper get(Object key) {
        log.debug("get key[{}] from conCurrentMapCache, use {ValueWrapper get(Object key)}.",key);
        Object value = store.get(key.toString());
        //这个地方如果没有查询到，注意返回null
        return Objects.isNull(value)? null :new SimpleValueWrapper(value);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        log.debug("get key[{}] from conCurrentMapCache, use {get(Object key, Class<T> type)}.",key);
        T value = (T)store.get(key.toString());
        return value;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        log.debug("get key[{}] from conCurrentMapCache, use {get(Object key, Callable<T> valueLoader)}.",key);
        Object storeValue= store.get(key.toString());
        T value = null;
        if(Objects.isNull(storeValue)){
            //如果未命中缓存，则应该在callabele的call方法中做 查询-缓存-返回 动作
            try {
                value = valueLoader.call();
            } catch (Exception e) {
                log.error("load key[{}] error!",key,e);
            }
        }else{
            value = (T)storeValue;
        }
        return value;
    }

    @Override
    public void put(Object key, Object value) {
        if(!checkKey(key)){
            return;
        }
        store.put(key.toString(),value);
    }

//    @Override
//    public ValueWrapper putIfAbsent(Object key, Object value) {
//        return null;
//    }

    @Override
    public void evict(Object key) {
        checkKey(key);
        store.remove(key.toString());
    }

//    @Override
//    public boolean evictIfPresent(Object key) {
//        return false;
//    }

    @Override
    public void clear() {
        store.clear();
    }


    private boolean checkKey(Object key){
        if(Objects.isNull(key)){
            log.error("key must not be nullable");
            return false;
        }
        if(StringUtils.isBlank(key.toString())){
            log.error("key must not be blank");
            return false;
        }
        return true;
    }
}
