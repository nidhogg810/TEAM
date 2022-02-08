package com.example.demo.cache.annotation;

import org.springframework.cache.annotation.CacheEvict;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface HCacheEvict {
}
