package com.tofba.blog.model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共常量
 * 
 * @author  Henry(fba02)
 * @version  [版本号, 2020年8月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BlogConstant {
    
    /**
     * 所有设置选项（key,value）
     */
    public static Map<String, String> OPTIONS = new HashMap<String, String>();
    
    /**
     * OwO表情
     */
    public static Map<String, String> OWO = new HashMap<String, String>();
    
    /**
     * 所有主题
     */
    public static List<Theme> THEMES = new ArrayList<Theme>();
    
    /**
     * user_session
     */
    public static String USER_SESSION_KEY = "user_session";
    
    /**
     * 文章阅读数缓存
     */
    public static Map<Long, Long> POSTS_VIEWS = new HashMap<Long, Long>();
}
