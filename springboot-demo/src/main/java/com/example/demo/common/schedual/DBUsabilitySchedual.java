package com.example.demo.common.schedual;

import com.example.demo.common.datasource.DataSourceType;
import com.example.demo.common.datasource.DynamicDataSource;
import com.example.demo.common.datasource.DynamicDataSourceContextHolder;
import com.example.demo.common.mapper.DbUsabilityMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class DBUsabilitySchedual {
    @Autowired
    private DbUsabilityMapper dbUsability;
    @Scheduled(fixedRate = 30000)
    public void dbUsability(){
        String dsType = DynamicDataSourceContextHolder.getDataSourceType();
        log.info("DBUsabilitySchedual#dbUsability() dstype:"+dsType);
        boolean DB1Usability = checkUsability(DataSourceType.DB1.name(),dsType);//DB1可用性
        boolean DB2Usability = checkUsability(DataSourceType.DB2.name(),dsType);//DB2可用性
        if (DataSourceType.DB1.name().equals(dsType)|| StringUtils.isBlank(dsType)) {//当前使用的数据源是否是db1
            //是db1
            if(DB1Usability){//DB1可用
                log.info("DBUsabilitySchedual#dbUsability() DB1 connect success");
            }else if(DB2Usability){//DB2可用，DB1不可用
                log.info("DBUsabilitySchedual#dbUsability() DB1 connect failure, DB2 connect success, ds change to DB2");
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
            }else{//均不可用
                log.info("DBUsabilitySchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
                DynamicDataSourceContextHolder.resetDataSourceType();
            }
        }else{
            if(DB1Usability){//DB1可用
                log.info("DBUsabilitySchedual#dbUsability() DB1 connect success, reset to DB1");
                DynamicDataSourceContextHolder.resetDataSourceType();
            }else if(DB2Usability){//DB2可用，DB1不可用
                log.info("DBUsabilitySchedual#dbUsability() DB1 connect failure, still to DB2");
//                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
            }else{//均不可用
                log.info("DBUsabilitySchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
                DynamicDataSourceContextHolder.resetDataSourceType();
            }
        }
    }


    private boolean checkUsability(String ds,String defualtDs){
        boolean flag = false;
        try {
            DynamicDataSourceContextHolder.setDataSourceType(ds,1);
            String dbresult = dbUsability.selectDual();
            if(!StringUtils.isBlank(dbresult)){
                flag = true;
            }
        }catch (Throwable t){
            log.error("DBUsabilitySchedual#checkUsability() error!select failed",t);
        }finally {
            DynamicDataSourceContextHolder.setDataSourceType(defualtDs,1);
        }
        return flag;
    }
}
