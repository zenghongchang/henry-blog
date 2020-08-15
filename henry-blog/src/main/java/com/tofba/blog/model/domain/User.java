package com.tofba.blog.model.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 博主信息
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "halo_user")
public class User implements Serializable {
    
    /**
     * 编号
     */
    @Id
    @GeneratedValue
    private Long userId;
    
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @JsonIgnore
    private String userName;
    
    /**
     * 显示名称
     */
    private String userDisplayName;
    
    /**
     * 密码
     */
    @JsonIgnore
    private String userPass;
    
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String userEmail;
    
    /**
     * 头像
     */
    private String userAvatar;
    
    /**
     * 说明
     */
    private String userDesc;
    
    /**
     * 是否禁用登录
     */
    @JsonIgnore
    private String loginEnable = "true";
    
    /**
     * 最后一次登录时间
     */
    @JsonIgnore
    private Date loginLast;
    
    /**
     * 登录错误次数记录
     */
    @JsonIgnore
    private Integer loginError = 0;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserDisplayName() {
        return userDisplayName;
    }
    
    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }
    
    public String getUserPass() {
        return userPass;
    }
    
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserAvatar() {
        return userAvatar;
    }
    
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
    
    public String getUserDesc() {
        return userDesc;
    }
    
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
    
    public String getLoginEnable() {
        return loginEnable;
    }
    
    public void setLoginEnable(String loginEnable) {
        this.loginEnable = loginEnable;
    }
    
    public Date getLoginLast() {
        return loginLast;
    }
    
    public void setLoginLast(Date loginLast) {
        this.loginLast = loginLast;
    }
    
    public Integer getLoginError() {
        return loginError;
    }
    
    public void setLoginError(Integer loginError) {
        this.loginError = loginError;
    }
}