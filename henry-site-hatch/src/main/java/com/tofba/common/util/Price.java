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

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * 价格相关的类
 * 
 * @author Henry(fba02)
 * @version [版本号, 2017年9月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Price {
    /**
     * 小数点后两位数四舍五入
     *
     * @param price 要四舍五入的价格
     *
     * @return double 四舍五入后的值
     * */
    public static Double round(Double price) {
        price = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();        
        return price;
    }
    
    /**
     * 小数点后四位数四舍五入
     *
     * @param price 要四舍五入的价格
     *
     * @return double 四舍五入后的值
     * */
    public static Double round(Double price, int precision) {
        price = new BigDecimal(price).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();        
        return price;
    }
    
    /**
     * 金额转换为大写
     *
     * */
    public static String digitUppercase(double n) {
        String fraction[] = {"角", "分"};
        String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String unit[][] = { {"元", "万", "亿"}, {"", "拾", "佰", "仟"}};
        String head = n < 0 ? "负" : "";
        n = Math.abs(n);        
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int)Math.floor(n);
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }        
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }    
    
    /**
     * 数字字符串格式化，小数点超过2位,四舍五入取2位小数
     * @param s
     * @return
     * @author  Jeff(fba01)
     * @version  [版本号, 2018年3月5日]
     * @see [类、类#方法、类#成员]
     */
    public static String formatString(String s) {
        if(StringUtils.isBlank(s) || s.indexOf(".") == -1) {
            return s;
        }
        int sternFirstIndex = s.indexOf(".") + 1;
        if((s.length() - sternFirstIndex) > 2) {//表示小数点超过2位,四舍五入取2位小数
            try {
                Double formulDouble = Double.parseDouble(s);
                formulDouble = Price.round(formulDouble);
                return formulDouble.toString();
            }
            catch (Exception e) {
                return s;
            }
        }
        return s;
    }
    
    /**
     * 数字字符串格式化，小数点超过length位,四舍五入取length位小数
     * <一句话功能简述>
     * <功能详细描述>
     * @param s
     * @param length
     * @return
     * @author  Jeff(fba01)
     * @version  [版本号, 2018年3月5日]
     * @see [类、类#方法、类#成员]
     */
    public static String formatString(String s,int length) {
        if(StringUtils.isBlank(s) || s.indexOf(".") == -1) {
            return s;
        }
        int sternFirstIndex = s.indexOf(".") + 1;
        if((s.length() - sternFirstIndex) > length) {
            try {
                Double formulDouble = Double.parseDouble(s);
                formulDouble = Price.round(formulDouble,length);
                return formulDouble.toString();
            }
            catch (Exception e) {
                return s;
            }
        }
        return s;
    }
    
    /**
     * 数字字符串格式化，小数点超过length位,四舍五入取length位小数
     * <一句话功能简述>
     * <功能详细描述>
     * @param s
     * @param length
     * @return
     * @author  Jeff(fba01)
     * @version  [版本号, 2018年3月5日]
     * @see [类、类#方法、类#成员]
     */
    public static Double fotmatStringToDouble(String s,int length) {
        s = formatString(s, length);
        if(StringUtils.isNotBlank(s)) {
            try {
                return Double.parseDouble(s);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return 0D;
    }
    
    /**
     * 数字字符串格式化，小数点超过2位,四舍五入取2位小数
     * @param s
     * @return
     * @author  Jeff(fba01)
     * @version  [版本号, 2018年3月5日]
     * @see [类、类#方法、类#成员]
     */
    public static Double fotmatStringToDouble(String s) {
        s = formatString(s);
        if(StringUtils.isNotBlank(s)) {
            try {
                return Double.parseDouble(s);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return 0D;
    }
}