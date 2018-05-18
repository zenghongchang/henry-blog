/**
 * Copyright © 2015-2017 ToFBA E-commerce logistics Co. LTD All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * ShenZhen ToFBA E-commerce logistics Co., LTD.
 * No body can copy or modify any part of this source without the permission of
 * ShenZhen ToFBA E-commerce logistics Co., LTD.
 *   _________    ___   ________  ______        _       
 *  |  _   _  | .'   `.|_   __  ||_   _ \      / \      
 *  |_/ | | \_|/  .-.  \ | |_ \_|  | |_) |    / _ \     
 *      | |    | |   | | |  _|     |  __'.   / ___ \    
 *     _| |_   \  `-'  /_| |_     _| |__) |_/ /   \ \_  
 *    |_____|   `.___.'|_____|   |_______/|____| |____|                                             
 */
package com.tofba.common.util;

public class ObjectUtil {
    /**
     * 检查是否为有效的数字<br>
     * 检查Double和Float是否为无限大，或者Not a Number<br>
     * 非数字类型和Null将返回true
     * 
     * @param obj 被检查类型
     * @return 检查结果，非数字类型和Null将返回true
     */
    public static boolean isValidIfNumber(Object obj) {
        if (obj != null && obj instanceof Number) {
            if (obj instanceof Double) {
                if (((Double)obj).isInfinite() || ((Double)obj).isNaN()) {
                    return false;
                }
            } else if (obj instanceof Float) {
                if (((Float)obj).isInfinite() || ((Float)obj).isNaN()) {
                    return false;
                }
            }
        }
        return true;
    }
}