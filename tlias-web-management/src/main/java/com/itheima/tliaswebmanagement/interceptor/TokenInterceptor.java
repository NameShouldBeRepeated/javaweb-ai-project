package com.itheima.tliaswebmanagement.interceptor;

import com.itheima.tliaswebmanagement.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String requestURI = request.getRequestURI();// /employee/login

        //如果是登录操作，直接放行
        if(requestURI.equals("/login")){
            log.info("登录操作，直接放行");
            return true;
        }
        //不是登录操作，继续执行
        //先获取请求头中的token
        String token = request.getHeader("token");

        //判断token是否存在。不存在的话，返回401
        if(token==null || token.isEmpty()){
            log.info("令牌为空，相应401");
            response.setStatus(401);
            return false;
        }
        //token存在。校验令牌
        try{
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("令牌非法，相应401");
            response.setStatus(401);
            return false;
        }

        //校验合格，放行
        log.info("令牌合法，放行");
        return true;
    }
}
