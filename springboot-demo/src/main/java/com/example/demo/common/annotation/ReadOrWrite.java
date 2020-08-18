package com.example.demo.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOrWrite {
    String value() default "read";
}
