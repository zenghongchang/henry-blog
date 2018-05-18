//package com.tofba.interceptor;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.tofba.common.system.UserSingleton;
//import com.tofba.common.util.IpUtil;
//import com.tofba.model.UserDomain;
//
///**
// * 自定义拦截器
// * 
// * @author Henry(fba02)
// * @version [版本号, 2018年5月17日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Component
//public class AuthenticationInteceptor implements HandlerInterceptor {
//    private static final Logger LOGGE = LoggerFactory.getLogger(AuthenticationInteceptor.class);
//    @Autowired
//    private UserSingleton userSingleton;    
//    
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
//        throws Exception {
//        LOGGE.info("UserAgent: {}", request.getHeader("user-agent"));
//        LOGGE.info("请求IP： {}", IpUtil.getIpAddress(request));
//        LOGGE.info("用户访问URL: {}", request.getRequestURI());
//        String uri = request.getRequestURI().substring(request.getContextPath().length());
//        if (allowAccessUrl(uri).booleanValue()) {
//            return true;
//        }
//        // 请求拦截处理
//        UserDomain userDomain = userSingleton.getUser();
//        if (null == userDomain) {
//            // 判断是否为AJAX请求,默认不是
//            if (!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
//                response.setHeader("noAuthentication", "true");// 无权限操作
//                return false;
//            }
//            if ("POST".equals(request.getMethod())) {
//                response.sendRedirect("/admin/timeout");
//                return false;
//            }
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return false;
//        }
//        return true;
//    }
//    
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
//        throws Exception {
//        
//    }
//    
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
//        throws Exception {
//    }
//    
//    /**
//     * URL白名单
//     * 
//     * @param paramString
//     * @return
//     * @author Henry(fba02)
//     * @version [版本号, 2018年5月17日]
//     * @see [类、类#方法、类#成员]
//     */
//    private Boolean allowAccessUrl(String paramString) {
//        ArrayList<String> localArrayList = new ArrayList<>();
//        localArrayList.add("/");
//        localArrayList.add("/admin/freeMarker");
//        localArrayList.add("/admin/login");// 登录
//        localArrayList.add("/admin/register");// 注册
//        localArrayList.add("/admin/logout");// 登出
//        localArrayList.add("/admin/timeout");// 请求超时
//        return Boolean.valueOf(localArrayList.contains(paramString));
//    }
//}