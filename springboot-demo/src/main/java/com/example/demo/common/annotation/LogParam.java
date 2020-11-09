package com.example.demo.common.annotation;

import java.lang.annotation.*;

/**
 * @author yuhan
 * customized annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogParam {
}
