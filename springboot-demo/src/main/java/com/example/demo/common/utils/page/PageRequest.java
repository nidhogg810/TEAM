package com.example.demo.common.utils.page;

import lombok.Data;

/**
 * 分页查询请求封装类
 */
@Data
public class PageRequest {
    //当前页码
    private int pageNum;
    //每页数量
    private int pageSize;


}
