/**
 * Copyright ToFBA Ecommerce Logistics LTD. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Shenzhen ToFBA Ecommerce Logistics Co., Ltd.
 * No body can copy or modify any part of this source without the permission of
 * Shenzhen ToFBA Ecommerce Logistics Co., Ltd.
 *   _________    ___   ________  ______        _       
 *  |  _   _  | .'   `.|_   __  ||_   _ \      / \      
 *  |_/ | | \_|/  .-.  \ | |_ \_|  | |_) |    / _ \     
 *      | |    | |   | | |  _|     |  __'.   / ___ \    
 *     _| |_   \  `-'  /_| |_     _| |__) |_/ /   \ \_  
 *    |_____|   `.___.'|_____|   |_______/|____| |____|
 *                                                
 */
package com.tofba.model;

/**
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserDomain {
    /** 主键编号 */
    private Integer uid;
    
    /** 用户名 */
    private String username;
    
    /** 密码 */
    private String password;
    
    /** email */
    private String email;
    
    /** 主页地址 */
    private String homeUrl;
    
    /** 用户显示的名称 */
    private String screenName;
    
    /** 用户注册时的GMT unix时间戳 */
    private Integer created;
    
    /** 最后活动时间 */
    private Integer activated;
    
    /** 上次登录最后活跃时间 */
    private Integer logged;
    
    /** 用户组 */
    private String groupName;
    
    private String remoteAddr;
    
    public Integer getUid() {
        return uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getHomeUrl() {
        return homeUrl;
    }
    
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }
    
    public String getScreenName() {
        return screenName;
    }
    
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    
    public Integer getCreated() {
        return created;
    }
    
    public void setCreated(Integer created) {
        this.created = created;
    }
    
    public Integer getActivated() {
        return activated;
    }
    
    public void setActivated(Integer activated) {
        this.activated = activated;
    }
    
    public Integer getLogged() {
        return logged;
    }
    
    public void setLogged(Integer logged) {
        this.logged = logged;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @Override
    public String toString() {
        return "UserDomain [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", homeUrl=" + homeUrl + ", screenName=" + screenName + ", created=" + created + ", activated=" + activated + ", logged=" + logged + ", groupName=" + groupName + ", remoteAddr=" + remoteAddr + "]";
    }
}