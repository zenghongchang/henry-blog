package com.tofba.blog.model.enums;

/**
 * true or false enum
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum TrueFalseEnum {
    
    /**
     * 真
     */
    TRUE("true"),
    
    /**
     * 假
     */
    FALSE("false");
    
    private String desc;
    
    TrueFalseEnum(String desc) {
        this.desc = desc;
    }
    
    public String getDesc() {
        return desc;
    }
}
