package com.example.demo.common.aspect;

import com.example.demo.common.annotation.LogParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author yuhan
 * aspect for customized annotation
 */
@Aspect
@Component
public class SigTestAspect {
    @Pointcut("execution(public * com.example.demo.contorller.*.*(java.lang.String,..))&&@annotation(com.example.demo.common.annotation.LogParam)")
    public void validate(){}
    @Around("validate()")
    public Object dolog(ProceedingJoinPoint joinPoint){
        Object[] params = joinPoint.getArgs();
        Object result = null;
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        LogParam logParam = ms.getMethod().getAnnotation(LogParam.class);
        String s = logParam.value();
        try {
            result = joinPoint.proceed(params);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        String resultString = (String) result;
        return result;
    }
}
