package com.tofba.common.system;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 缓存Map
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("sessionMap")
public class SessionMap {
    @Autowired
    private HttpSession session;
    
    public Object get(String paramString) {
        return this.session.getAttribute(paramString);
    }
    
    public void put(String paramString, Object paramObject) {
        this.session.setAttribute(paramString, paramObject);
    }
    
    public void destory(String paramString) {
        this.session.removeAttribute(paramString);
    }
    
    public void destoryAll() {
        this.session.invalidate();
    }
    
    public HttpSession getSession() {
        return this.session;
    }
    
    public void setSession(HttpSession paramHttpSession) {
        this.session = paramHttpSession;
    }
}