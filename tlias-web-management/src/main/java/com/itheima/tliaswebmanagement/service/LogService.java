package com.itheima.tliaswebmanagement.service;


import com.itheima.tliaswebmanagement.pojo.OperateLog;
import com.itheima.tliaswebmanagement.pojo.PageResult;


public interface LogService {
    /**
     * 分页查询日志
     * @param page 页码
     * @param pageSize 每页数据多少
     * @return
     */
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
