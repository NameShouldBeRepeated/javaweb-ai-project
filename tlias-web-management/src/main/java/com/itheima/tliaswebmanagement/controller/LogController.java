package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.pojo.OperateLog;
import com.itheima.tliaswebmanagement.pojo.PageResult;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/log/page")
    public Result selectLog(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("日志分页查询 页码 {} 页数大小{}",page,pageSize);
        PageResult<OperateLog> pageResult = logService.page(page,pageSize);
        return Result.success(pageResult);
    }
}
