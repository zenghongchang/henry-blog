package com.tofba.blog.web.interceptor;

import cn.hutool.core.util.StrUtil;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.model.enums.BlogPropertiesEnum;
import com.tofba.blog.model.enums.LocaleEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

/**
 * @author : wangry
 * @version : 1.0
 * @date : 2018年09月08日
 */
@Component
public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StrUtil.equals(LocaleEnum.EN_US.getValue(), BlogConstant.OPTIONS.get(BlogPropertiesEnum.BLOG_LOCALE.getProp()))) {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en", "US"));
        } else {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh", "CN"));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
