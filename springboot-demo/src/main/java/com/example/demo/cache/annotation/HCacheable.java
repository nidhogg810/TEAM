package com.example.demo.cache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author yuhan
 * HCache 作为一个 field key value 的数据结构来存储
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface HCacheable {
    @AliasFor("cacheNames")
    String[] value() default {};
    @AliasFor("value")
    String[] cacheNames() default {};
    String key() default "";
    String keyGenerator() default "";
    String cacheManager() default "";
    String cacheResolver() default "";
    String condition() default "";
    String unless() default "";
    boolean sync() default false;
    String cacheField();
}
