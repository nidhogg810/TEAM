package com.example.demo.contorller;


import com.example.demo.common.constants.CacheConstants;
import com.example.demo.common.mapper.DbUsabilityMapper;
import com.example.demo.common.mapper.EmployeesMapper;
import com.example.demo.common.model.EmployeesModel;
import com.example.demo.service.GetEmpInfoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {


    @Autowired
    private GetEmpInfoServ getEmpInfoServ;

    @RequestMapping("employee/getAllInfo")
    public List<EmployeesModel> getAllEmpInfo(){
        return getEmpInfoServ.findAll();
    }
    @RequestMapping("employee/cnt")
    public int getEmpNum(){
        return getEmpInfoServ.getEmployeesNum();
    }
    @Autowired
    private DbUsabilityMapper dbUsability;
    @RequestMapping("employee/db")
    public String getDb(){
        return dbUsability.selectDual();
    }
    @RequestMapping(value="employee/findPage",method = RequestMethod.GET)
    public Object findPage(@RequestParam (value = "pageNum") int pageNum,@RequestParam(value = "pageSize") int pageSize){
        return getEmpInfoServ.getPageInfo(pageNum,pageSize);
    }
    @Autowired
    EmployeesMapper  employeesMapper;
    @RequestMapping(value = "employee/test",method = RequestMethod.GET)
    @Cacheable(value = CacheConstants.CONCURRENTMAP_CACHE,key = "#id")
    public int test(@RequestParam(value = "id") String id){
        return employeesMapper.countEmployees();
    }
}
