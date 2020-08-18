package com.example.demo.common.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DynamicDataSourceContextHolder {
    private static final String DEFAULT_DATASOURCE = DataSourceType.DB1.name();
    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static String CONTEXT_HOLDER = DEFAULT_DATASOURCE;

    private static final ThreadLocal<String> THREAD_CONTEXT_HOLERS = new ThreadLocal<>();

    /**
     * 设置数据源的变量
     */
    public static void setDataSourceType(String dsType){
//        log.info("切换到{}数据源", dsType);
//        CONTEXT_HOLDER = dsType;
        setDataSourceType(dsType,0);
    }

    public static void setDataSourceType(String dsType,int ThreadFlag){
//        log.info("change datasource{}",dsType);
        THREAD_CONTEXT_HOLERS.set(dsType);
        if (0 == ThreadFlag){
            log.info("change datasource {} all thread",dsType);
            CONTEXT_HOLDER = dsType;
        }else{
            log.info("change datasource {} this thread",dsType);
        }
    }
    /**
     * 获得数据源的变量
     */
    public static String getDataSourceType(){
        String holder = THREAD_CONTEXT_HOLERS.get();
        if(StringUtils.isBlank(holder)){
            holder = CONTEXT_HOLDER;
        }
        return holder;
    }

    /**
     * 复位数据源变量
     */
    public static void resetDataSourceType() {
        log.info("切换到{}数据源", DEFAULT_DATASOURCE);
        THREAD_CONTEXT_HOLERS.remove();
        CONTEXT_HOLDER = DEFAULT_DATASOURCE;
    }

    public static void resetDataSourceType(int ThreadFlag){
        THREAD_CONTEXT_HOLERS.set(DEFAULT_DATASOURCE);
        if (0 == ThreadFlag){
            log.info("reset for all thread");
            CONTEXT_HOLDER = DEFAULT_DATASOURCE;
        }
    }
}
