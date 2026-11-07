package com.itheima.tliaswebmanagement.config;

import com.itheima.tliaswebmanagement.interceptor.DemoInterceptor;
import com.itheima.tliaswebmanagement.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//说明这是一个配置类
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private DemoInterceptor demoInterceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")//拦截请求
                .excludePathPatterns("/login");//不拦截哪些请求
    }
}
