package com.itheima.tliaswebmanagement.filter;

import com.itheima.tliaswebmanagement.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletresponse;
        HttpServletRequest request = (HttpServletRequest) servletrequest;
        //获取请求路径
        String requestURI = request.getRequestURI();// /employee/login

        //如果是登录操作，直接放行
        if(requestURI.equals("/login")){
            log.info("登录操作，直接放行");
            chain.doFilter(servletrequest,servletresponse);
            return ;
        }
        //不是登录操作，继续执行
        //先获取请求头中的token
        String token = request.getHeader("token");

        //判断token是否存在。不存在的话，返回401
        if(token==null || token.isEmpty()){
            log.info("令牌为空，相应401");
            response.setStatus(401);
            return;
        }
        //token存在。校验令牌
        try{
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("令牌非法，相应401");
            response.setStatus(401);
            return;
        }

        //校验合格，放行
        log.info("令牌合法，放行");
        chain.doFilter(servletrequest,servletresponse);
    }
}
