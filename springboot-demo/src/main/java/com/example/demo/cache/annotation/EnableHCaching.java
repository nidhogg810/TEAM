package com.example.demo.cache.annotation;

import com.example.demo.common.config.selector.HCachingConfigurationSelector;
import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HCachingConfigurationSelector.class)
public @interface EnableHCaching {
}
