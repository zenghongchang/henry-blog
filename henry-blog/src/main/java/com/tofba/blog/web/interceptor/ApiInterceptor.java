package com.tofba.blog.web.interceptor;

import cn.hutool.core.util.StrUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.model.enums.BlogPropertiesEnum;
import com.tofba.blog.model.enums.TrueFalseEnum;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     API接口拦截器，用户可自己选择关闭或者开启
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/6/28
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StrUtil.equals(TrueFalseEnum.TRUE.getDesc(), BlogConstant.OPTIONS.get(BlogPropertiesEnum.API_STATUS.getProp()))) {
            if (StrUtil.equals(request.getHeader("token"), BlogConstant.OPTIONS.get(BlogPropertiesEnum.API_TOKEN.getProp()))) {
                return true;
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                Map<String, Object> map = new HashMap<>(2);
                ObjectMapper mapper = new ObjectMapper();
                map.put("code", 400);
                map.put("msg", "Invalid Token");
                response.getWriter().write(mapper.writeValueAsString(map));
                return false;
            }
        }
        response.sendRedirect("/404");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
