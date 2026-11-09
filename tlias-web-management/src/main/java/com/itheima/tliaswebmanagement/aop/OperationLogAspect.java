package com.itheima.tliaswebmanagement.aop;


import com.itheima.tliaswebmanagement.mapper.OperateLogMapper;
import com.itheima.tliaswebmanagement.pojo.OperateLog;
import com.itheima.tliaswebmanagement.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper  operateLogMapper;

    @Around("@annotation(com.itheima.tliaswebmanagement.anno.Log)")
    public Object LogOperation(ProceedingJoinPoint pjp) throws Throwable{
        long  startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(CurrentHolder.getCurrentId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(pjp.getTarget().getClass().getName());
        olog.setMethodName(pjp.getSignature().getName());
        olog.setMethodParams(Arrays.toString(pjp.getArgs()));
        olog.setReturnValue(result == null?"void":result.toString());
        olog.setCostTime(time);

        operateLogMapper.insert(olog);
        log.info("记录操作日志：{}",olog);

        return  result;
    }
}
