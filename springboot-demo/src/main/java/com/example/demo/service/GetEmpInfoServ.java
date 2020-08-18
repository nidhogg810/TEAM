package com.example.demo.service;

import com.example.demo.common.datasource.DataSourceType;
import com.example.demo.common.datasource.DynamicDataSourceContextHolder;
import com.example.demo.common.mapper.EmployeesMapper;
import com.example.demo.common.model.EmployeesModel;
import com.example.demo.common.utils.page.PageRequest;
import com.example.demo.common.utils.page.PageResult;
import com.example.demo.common.utils.page.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetEmpInfoServ {
    @Autowired
    private EmployeesMapper employeesMapper;

    public List<EmployeesModel> findAll(){
        return employeesMapper.selectAll();
    }

    public int getEmployeesNum() {
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
        return employeesMapper.countEmployees();
    }

    /**
     * 调用分页插件完成分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<EmployeesModel> getPageInfo(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<EmployeesModel> empList = employeesMapper.selectPage();
        return new PageInfo<EmployeesModel>(empList);
    }


}
