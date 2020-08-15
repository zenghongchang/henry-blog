package com.tofba.blog.model.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 操作日志
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "halo_logs")
public class Logs implements Serializable {
    
    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long logId;
    
    /**
     * 标题
     */
    private String logTitle;
    
    /**
     * 内容
     */
    private String logContent;
    
    /**
     * 产生日志的ip
     */
    private String logIp;
    
    /**
     * 产生的时间
     */
    private Date logCreated;
    
    public Logs() {
    }
    
    public Logs(String logTitle, String logContent, String logIp, Date logCreated) {
        this.logTitle = logTitle;
        this.logContent = logContent;
        this.logIp = logIp;
        this.logCreated = logCreated;
    }
    
    public Long getLogId() {
        return logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    
    public String getLogTitle() {
        return logTitle;
    }
    
    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }
    
    public String getLogContent() {
        return logContent;
    }
    
    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
    
    public String getLogIp() {
        return logIp;
    }
    
    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }
    
    public Date getLogCreated() {
        return logCreated;
    }
    
    public void setLogCreated(Date logCreated) {
        this.logCreated = logCreated;
    }
}
