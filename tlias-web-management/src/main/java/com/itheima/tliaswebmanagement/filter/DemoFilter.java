package com.itheima.tliaswebmanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")//拦截所有请求
public class DemoFilter implements Filter {

    //初始化 只会在web服务器启动时运行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法运行了");
    }

    //拦截到请求后就执行
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截到请求");
        //放行
        chain.doFilter(request,response);
    }

    //销毁 只会在web服务器关闭时运行一次
    @Override
    public void destroy() {
        log.info("destory 销毁方法运行了");
    }
}
