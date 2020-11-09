package com.example.demo.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yuhan
 * aspect for customized annotation
 */
@Aspect
@Component
public class SigTestAspect {
    @Pointcut("execution(public * com.example.demo.contorller.*.*(..))&&@annotation(com.example.demo.common.annotation.LogParam)")
    public void validate(){}
    @Around("validate()")
    public void dolog(ProceedingJoinPoint joinPoint){
        Object params = joinPoint.getArgs();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
