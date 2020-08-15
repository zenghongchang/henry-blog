package com.tofba.blog.model.enums;

/**
 * 返回结果enum
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum ResultCodeEnum {
    
    /**
     * 成功
     */
    SUCCESS(1),
    
    /**
     * 失败
     */
    FAIL(0);
    
    Integer code;
    
    ResultCodeEnum(Integer code) {
        this.code = code;
    }
    
    public Integer getCode() {
        return code;
    }
}
