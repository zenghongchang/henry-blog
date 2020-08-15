package com.tofba.blog.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.tofba.blog.web.interceptor.ApiInterceptor;
import com.tofba.blog.web.interceptor.InstallInterceptor;
import com.tofba.blog.web.interceptor.LocaleInterceptor;
import com.tofba.blog.web.interceptor.LoginInterceptor;

/**
 * 拦截器，资源路径配置
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cc.ryanc.halo.web.controller")
@PropertySource(value = "classpath:application.yaml", ignoreResourceNotFound = true, encoding = "UTF-8")
public class WebMvcAutoConfiguration implements WebMvcConfigurer {    
    @Autowired
    private LoginInterceptor loginInterceptor;    
    @Autowired
    private InstallInterceptor installInterceptor;    
    @Autowired
    private ApiInterceptor apiInterceptor;    
    @Autowired
    private LocaleInterceptor localeInterceptor;
    
    /**
     * 注册拦截器
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**").addPathPatterns("/backup/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/getLogin").excludePathPatterns("/static/**");
        registry.addInterceptor(installInterceptor).addPathPatterns("/**").excludePathPatterns("/install").excludePathPatterns("/install/do").excludePathPatterns("/static/**");
        // registry.addInterceptor(apiInterceptor)
        // .addPathPatterns("/api/**");
        registry.addInterceptor(localeInterceptor).addPathPatterns("/admin/**").addPathPatterns("/install");
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/install");
    }
    
    /**
     * 配置静态资源路径
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/themes/").addResourceLocations("classpath:/robots.txt");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + System.getProperties().getProperty("user.home") + "/halo/upload/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/halo-backend/images/favicon.ico");
        registry.addResourceHandler("/backup/**").addResourceLocations("file:///" + System.getProperties().getProperty("user.home") + "/halo/backup/");
    }
    
    /**
     * 跨域
     *
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedHeaders("*").allowedOrigins("*").allowedMethods("GET", "POST").exposedHeaders("access-control-allow-headers", "access-control-allow-methods", "access-control-allow-origin", "access-control-max-age", "X-Frame-Options", "token").allowCredentials(false).maxAge(3600);
    }
    
    /**
     * 国际化设置
     *
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }
    
    /**
     * 国际化参数拦截器
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}