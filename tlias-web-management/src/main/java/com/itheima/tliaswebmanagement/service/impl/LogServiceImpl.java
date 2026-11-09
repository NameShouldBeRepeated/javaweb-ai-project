package com.itheima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.tliaswebmanagement.mapper.LogMapper;
import com.itheima.tliaswebmanagement.pojo.OperateLog;
import com.itheima.tliaswebmanagement.pojo.PageResult;
import com.itheima.tliaswebmanagement.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    /**
     * 分页查询日志
     *
     * @param page     页码
     * @param pageSize 每页数据多少
     * @return
     */
    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> list = logMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) list;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
