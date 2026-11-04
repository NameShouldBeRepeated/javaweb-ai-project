package com.itheima.tliaswebmanagement.expection;

import com.itheima.tliaswebmanagement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExpectionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错了！",e);
        return Result.error("服务器出现问题，请联系管理员");
    }
}
