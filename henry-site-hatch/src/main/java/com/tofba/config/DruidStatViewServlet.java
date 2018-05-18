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
package com.tofba.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * Druid监控视图配置
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {@WebInitParam(name = "allow", value = "192.168.180.182"),// IP白名单 (没有配置或者为空,则允许所有访问)
    @WebInitParam(name = "deny", value = "192.168.180.184"),// IP黑名单 (存在共同时,deny优先于allow)
    @WebInitParam(name = "loginUsername", value = "admin"),// 用户名
    @WebInitParam(name = "loginPassword", value = "admin"),// 密码
    @WebInitParam(name = "resetEnable", value = "true")// 禁用HTML页面上的"Reset All"功能
})
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}