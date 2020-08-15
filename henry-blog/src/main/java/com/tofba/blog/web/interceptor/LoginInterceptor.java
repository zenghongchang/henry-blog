package com.tofba.blog.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tofba.blog.model.dto.BlogConstant;

/**
 * 后台控制器拦截器
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年7月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {       
        // 验证是否登陆
        Object obj = request.getSession().getAttribute(BlogConstant.USER_SESSION_KEY);
        // 如果user不为空则放行
        if (null != obj) {
            return true;
        }
        // 否则拦截并跳转到登录
        response.sendRedirect("/admin/login");
        return false;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
    }
    
    public void returnJsonResult(HttpServletResponse response, String data) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
}