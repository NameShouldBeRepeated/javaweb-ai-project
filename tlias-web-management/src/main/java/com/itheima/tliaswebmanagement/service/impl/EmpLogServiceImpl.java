package com.itheima.tliaswebmanagement.service.impl;

import com.itheima.tliaswebmanagement.mapper.EmpLogMapper;
import com.itheima.tliaswebmanagement.pojo.EmpLog;
import com.itheima.tliaswebmanagement.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    //意思是需要事务，而且会自己开一个事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
