package com.tofba.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SystemInfo {
    
    public static String getAppPath() {
        return getAppPath(null);
    }
    
    public static String getAppPath(ServletContext sc) {
        String appPath;
        try {
            if (null == sc) {
                WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
                sc = webApplicationContext.getServletContext();
            }
            appPath = sc.getRealPath("/");
            if (!appPath.endsWith("/")) {
                appPath += "/";
            }
        } catch (Exception e) {
            e.printStackTrace();
            appPath = "";
        }
        return appPath;
    }
    
    public static String getContextPath() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getContextPath();
    }
    
    public static String getScriptPath() {
        return getAppPath() + "WEB-INF/classes/com/tofba/script/";
    }
    
    public static String getClassesPath() {
        return getAppPath() + "WEB-INF/classes/";
    }
    
    public static String getClassesPath(ServletContext sc) {
        return getAppPath(sc) + "WEB-INF/classes/";
    }
    
    public static String getTofbaPath() {
        return getAppPath() + "WEB-INF/classes/com/tofba/";
    }
}