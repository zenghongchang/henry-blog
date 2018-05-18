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
package com.tofba.service.user;

import com.tofba.model.UserDomain;

public interface UserService {
    /*
     * 更新用户信息
     */
    int updateUserInfo(UserDomain user);
    
    /*
     * 根据ID查询用户信息
     */
    UserDomain getUserInfoById(Integer uId);
    
    /*
     * 校验用户信息
     */
    UserDomain validationUser(String username, String password);
    
    UserDomain findByUserName(String userName);
}