package com.example.demo.common.utils.page;

import com.github.pagehelper.PageInfo;

/**
 * 分页查询工具类
 */
public class PageUtils {
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo){
//        pageRequest.setPageNum(pageInfo.getPageNum());
//        pageRequest.setPageSize(pageInfo.getPageSize());
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setTotalSize(pageInfo.getPages());
        pageResult.setContext(pageInfo.getList());
        return pageResult;
    }
}
