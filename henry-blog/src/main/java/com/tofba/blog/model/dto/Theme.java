package com.tofba.blog.model.dto;

import java.io.Serializable;

/**
 * 主题信息
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
public class Theme implements Serializable {    
    /**
     * 主题名称
     */
    private String themeName;
    
    /**
     * 是否支持设置
     */
    private boolean hasOptions;
    
    /**
     * 是否支持更新
     */
    private boolean hasUpdate;
    
    public String getThemeName() {
        return themeName;
    }
    
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    
    public boolean isHasOptions() {
        return hasOptions;
    }
    
    public void setHasOptions(boolean hasOptions) {
        this.hasOptions = hasOptions;
    }
    
    public boolean isHasUpdate() {
        return hasUpdate;
    }
    
    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }    
}