package com.tofba.blog.model.dto;


/**
 * 七牛上传自定义凭证回调解析
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class QiNiuPutSet {    
    private Long size;    
    private Integer w;    
    private Integer h;
    
    public Long getSize() {
        return size;
    }
    
    public void setSize(Long size) {
        this.size = size;
    }
    
    public Integer getW() {
        return w;
    }
    
    public void setW(Integer w) {
        this.w = w;
    }
    
    public Integer getH() {
        return h;
    }
    
    public void setH(Integer h) {
        this.h = h;
    }    
}