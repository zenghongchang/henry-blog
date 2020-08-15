package com.tofba.blog.model.enums;

/**
 * 文章状态enum
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum PostStatusEnum {
    
    /**
     * 已发布
     */
    PUBLISHED(0, "已发布"),
    
    /**
     * 草稿
     */
    DRAFT(1, "草稿"),
    
    /**
     * 回收站
     */
    RECYCLE(2, "回收站");
    
    private Integer code;
    
    private String desc;
    
    PostStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getDesc() {
        return desc;
    }
}
