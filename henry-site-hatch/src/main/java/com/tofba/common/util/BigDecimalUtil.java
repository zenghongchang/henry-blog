package com.tofba.common.util;

import java.math.BigDecimal;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入
 * @author tomtop2461
 *
 */
public class BigDecimalUtil {

    /**
     * 加法
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1, double d2){        
         BigDecimal b1 = new BigDecimal(d1);
         BigDecimal b2 = new BigDecimal(d2);
         return b1.add(b2).doubleValue();
    }
    
    /**
     * 进行减法运算
     * @param d1   被减数
     * @param d2   减数
     * @return
     */
    public static double sub(double d1, double d2){        
         BigDecimal b1 = new BigDecimal(d1);
         BigDecimal b2 = new BigDecimal(d2);
         return b1.subtract(b2).doubleValue();
    }
    /**
     * 乘法运算
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1, double d2){       
         BigDecimal b1 = new BigDecimal(d1);
         BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }
    
    /**
     * 乘法运算
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1, double d2, double d3){       
         BigDecimal b1 = new BigDecimal(d1);
         BigDecimal b2 = new BigDecimal(d2);
         BigDecimal b3 = new BigDecimal(d3);
        return b1.multiply(b2).multiply(b3).doubleValue();
    }
    
    /**
     * 除法
     * @param d1 被除数
     * @param d2
     * @param len  精度
     * @return
     */
     public static double div(double d1,double d2,int len) {// 进行除法运算
         BigDecimal b1 = new BigDecimal(d1);
         BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
      }
     
    /**
     * 进行四舍五入操作
     * @param d
     * @param len
     * @return
     */
    public static double round(double d,int len) {
         BigDecimal b1 = new BigDecimal(d);
         BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
        return b1.divide(b2, len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
