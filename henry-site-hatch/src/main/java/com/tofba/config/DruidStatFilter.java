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

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 配置监控拦截器 druid监控拦截器
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),})
// 忽略资源
public class DruidStatFilter extends WebStatFilter {
}