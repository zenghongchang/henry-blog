//package com.tofba.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.tofba.interceptor.AuthenticationInteceptor;
//
///**
// * 拦截器配置
// * 
// * @author Henry(fba02)
// * @version [版本号, 2018年5月17日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Component
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Autowired
//    private AuthenticationInteceptor baseInterceptor;
//    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(baseInterceptor);
//    }
//}