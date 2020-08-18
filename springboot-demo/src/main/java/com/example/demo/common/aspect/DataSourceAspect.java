package com.example.demo.common.aspect;

import com.example.demo.common.annotation.ReadOrWrite;
import com.example.demo.common.datasource.DataSourceType;
import com.example.demo.common.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)//将aop放在处理链的最前面
public class DataSourceAspect {
    @Pointcut("@annotation(com.example.demo.common.annotation.ReadOrWrite)")
    public void annotationPointCut(){
    }
    @Before(value = "annotationPointCut()&&@annotation(type)")
    public void beforeSwitchDS(JoinPoint point, ReadOrWrite type){
        if ("read".equals(type.value())){
            //使用读数据库
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name(),1);
        }else if("write".equals(type.value())){
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB1.name(),1);
        }
    }

    @After(value = "annotationPointCut()")
    public void afterSwitchDS(JoinPoint point){
        DynamicDataSourceContextHolder.resetDataSourceType(1);
    }
}
