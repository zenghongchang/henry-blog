package com.tofba.blog.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 系统设置
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
@Table(name = "halo_options")
@Entity
public class Options implements Serializable {
    /**
     * 设置项名称
     */
    @Id
    @Column(length = 127)
    private String optionName;
    
    /**
     * 设置项的值
     */
    @Lob
    private String optionValue;
    
    public String getOptionName() {
        return optionName;
    }
    
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
    
    public String getOptionValue() {
        return optionValue;
    }
    
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }    
}