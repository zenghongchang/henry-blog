package com.tofba.common.system;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tofba.common.util.Json;
import com.tofba.model.UserDomain;

/**
 * 用户缓存实例
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("userSingleton")
public class UserSingleton {
    @Autowired
    private SessionMap sessionMap;
    
    public UserDomain getUser() {
        if (null == this.sessionMap) {
            try {
                this.sessionMap = new SessionMap();
                this.sessionMap.setSession(((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getSession());
            } catch (Exception localException) {
                return null;
            }
        }
        return ((UserDomain)Json.fromJson(Json.toJson(this.sessionMap.get("user-session")), UserDomain.class));
    }
    
    /**
     * 登陆信息存储至缓存
     * 
     * @param paramObject
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    public void setUser(Object paramObject) {
        this.sessionMap.put("user-session", paramObject);
    }
    
    /**
     * 销毁缓存
     * 
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    public void unsetUser() {
        this.sessionMap.destoryAll();
    }
    
    /**
     * 是否已经登陆
     * 
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    public Boolean isLogined() {
        return Boolean.valueOf(null != getUser());
    }
    
    /**
     * 判断是否是超级管理员
     * 
     * @param paramString
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    public Boolean isSuperUser(String paramString) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Henry");
        return Boolean.valueOf(hashSet.contains(paramString));
    }
    
    public Boolean isSuperUser() {
        UserDomain user = getUser();
        if (user == null) {
            return false;
        }
        return isSuperUser(getUser().getScreenName());
    }
}