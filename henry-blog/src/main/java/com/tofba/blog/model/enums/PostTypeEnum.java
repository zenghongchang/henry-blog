package com.tofba.blog.model.enums;

/**
 * 文章类型enum
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum PostTypeEnum {
    
    /**
     * 文章
     */
    POST_TYPE_POST("post"),
    
    /**
     * 页面
     */
    POST_TYPE_PAGE("page");
    
    private String desc;
    
    PostTypeEnum(String desc) {
        this.desc = desc;
    }
    
    public String getDesc() {
        return desc;
    }
}
