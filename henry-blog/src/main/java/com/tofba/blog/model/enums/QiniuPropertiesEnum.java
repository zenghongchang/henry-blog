package com.tofba.blog.model.enums;

/**
 * 七牛配置enum
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum QiniuPropertiesEnum {
    
    /**
     * 七牛云域名
     */
    QINIU_DOMAIN("qiniu_domain"),
    
    /**
     * 七牛云AccessKey
     */
    QINIU_ACCESS_KEY("qiniu_access_key"),
    
    /**
     * 七牛云SecretKey
     */
    QINIU_SECRET_KEY("qiniu_secret_key"),
    
    /**
     * 七牛云空间名
     */
    QINIU_BUCKET("qiniu_bucket");
    
    private String prop;
    
    QiniuPropertiesEnum(String prop) {
        this.prop = prop;
    }
    
    public String getProp() {
        return prop;
    }
}
