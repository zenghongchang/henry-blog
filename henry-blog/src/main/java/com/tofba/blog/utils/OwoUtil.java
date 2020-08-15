package com.tofba.blog.utils;

import com.tofba.blog.model.dto.BlogConstant;

/**
 * OwO表情工具类
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年7月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class OwoUtil {
    
    /**
     * 将表情标志转化为图片地址
     *
     * @param mark 表情标志
     * @return 表情图片地址
     */
    public static String markToImg(String mark) {
        for (String key : BlogConstant.OWO.keySet()) {
            mark = mark.replace(key, BlogConstant.OWO.get(key));
        }
        return mark;
    }
}
