package com.example.demo.cache.interceptor;

import org.springframework.cache.interceptor.CacheEvictOperation;

public class HCacheEvictOperation extends CacheEvictOperation {

    private final String cacheField;
    /**
     * Create a new {@link CacheEvictOperation} instance from the given builder.
     *
     * @param b
     * @since 4.3
     */
    public HCacheEvictOperation(Builder b) {
        super(b);
        this.cacheField = b.cacheField;
    }

    public static class Builder extends CacheEvictOperation.Builder{
        private String cacheField;

        public void setCacheField(String cacheField) {
            this.cacheField = cacheField;
        }
    }
}
