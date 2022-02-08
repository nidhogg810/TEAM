package com.example.demo.cache.interceptor;

import org.springframework.cache.interceptor.CacheOperation;
import org.springframework.cache.interceptor.CacheableOperation;
import org.springframework.lang.Nullable;

public class HCacheableOperation extends CacheableOperation {

    private final String cacheField;
    /**
     * Create a new {@link CacheOperation} instance from the given builder.
     *
     * @param b
     * @since 4.3
     */
    protected HCacheableOperation(Builder b) {
        super(b);
        this.cacheField = b.cacheField;
    }



    public static class Builder extends CacheableOperation.Builder{
        @Nullable
        private String unless;

        private boolean sync;

        private String cacheField;

        @Override
        public void setUnless(String unless) {
            this.unless = unless;
        }

        @Override
        public void setSync(boolean sync) {
            this.sync = sync;
        }

        public void setCacheField(String cacheField) {
            this.cacheField = cacheField;
        }

        @Override
        public CacheableOperation build() {
           return new HCacheableOperation(this);
        }
    }
}
