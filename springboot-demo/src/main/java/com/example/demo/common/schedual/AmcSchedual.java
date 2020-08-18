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

@Component
@Slf4j
public class AmcSchedual {
    @Autowired
    private DbUsabilityMapper dbUsability;
    @Scheduled(fixedRate = 30000)
    public void dbUsability(){
//        String dbresult = dbUsability.selectDual();
//        String dsType = DynamicDataSourceContextHolder.getDataSourceType();
//        log.info("AmcSchedual#dbUsability() dstype:"+dsType);
//        if (DataSourceType.DB1.name().equals(dsType)|| StringUtils.isBlank(dsType)){//当前使用的数据源是否时db1
//            //是DB1
//            if(checkUsability()){//检查当前使用的数据源是否可用
//                log.info("AmcSchedual#dbUsability() DB1 connect success");
//            }else{//当前使用的数据源不可用了
//                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name(),1);
////                dbresult = dbUsability.selectDual();
//                if (checkUsability()){
//                    log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect success, ds change to DB2");
//                    DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
//                }else{
//                    log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
//                    DynamicDataSourceContextHolder.resetDataSourceType();
//                }
//            }
//        }else{
//            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB1.name(),1);
////            String dbresultMaster = dbUsability.selectDual();
//            if (checkUsability()){
//                log.info("AmcSchedual#dbUsability() DB1 connect success, reset to DB1");
//                DynamicDataSourceContextHolder.resetDataSourceType();
//            }else {
//                if(checkUsability()){
//                    log.info("AmcSchedual#dbUsability() DB1 connect failure, still to DB2");
//                    DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
//                }else {
//                    log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
//                    DynamicDataSourceContextHolder.resetDataSourceType();
//                }
//            }
//        }
        String dsType = DynamicDataSourceContextHolder.getDataSourceType();
        log.info("AmcSchedual#dbUsability() dstype:"+dsType);
        boolean DB1Usability = checkUsability(DataSourceType.DB1.name(),dsType);//DB1可用性
        boolean DB2Usability = checkUsability(DataSourceType.DB2.name(),dsType);//DB2可用性
        if (DataSourceType.DB1.name().equals(dsType)|| StringUtils.isBlank(dsType)) {//当前使用的数据源是否是db1
            //是db1
            if(DB1Usability){//DB1可用
                log.info("AmcSchedual#dbUsability() DB1 connect success");
            }else if(DB2Usability){//DB2可用，DB1不可用
                log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect success, ds change to DB2");
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
            }else{//均不可用
                log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
                DynamicDataSourceContextHolder.resetDataSourceType();
            }
        }else{
            if(DB1Usability){//DB1可用
                log.info("AmcSchedual#dbUsability() DB1 connect success, reset to DB1");
                DynamicDataSourceContextHolder.resetDataSourceType();
            }else if(DB2Usability){//DB2可用，DB1不可用
                log.info("AmcSchedual#dbUsability() DB1 connect failure, still to DB2");
//                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
            }else{//均不可用
                log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
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
            log.error("AmcSchedual#checkUsability() error!select failed",t);
        }finally {
            DynamicDataSourceContextHolder.setDataSourceType(defualtDs,1);
        }
        return flag;
    }
}
