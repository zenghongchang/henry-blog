package com.tofba.blog.model.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附件
 * 
 * @author  Henry(fba02)
 * @version  [版本号, 2020年8月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "halo_attachment")
public class Attachment implements Serializable {
    /**
     * 附件编号
     */
    @Id
    @GeneratedValue
    private Long attachId;
    
    /**
     * 附件名
     */
    private String attachName;
    
    /**
     * 附件路径
     */
    private String attachPath;
    
    /**
     * 附件缩略图路径
     */
    private String attachSmallPath;
    
    /**
     * 附件类型
     */
    private String attachType;
    
    /**
     * 附件后缀
     */
    private String attachSuffix;
    
    /**
     * 上传时间
     */
    private Date attachCreated;
    
    /**
     * 附件大小
     */
    private String attachSize;
    
    /**
     * 附件长宽
     */
    private String attachWh;
    
    /**
     * 附件存储地址
     */
    private String attachLocation;
    
    /**
     * 附件来源，0：上传，1：外部链接
     */
    private Integer attachOrigin = 0;

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getAttachSmallPath() {
        return attachSmallPath;
    }

    public void setAttachSmallPath(String attachSmallPath) {
        this.attachSmallPath = attachSmallPath;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getAttachSuffix() {
        return attachSuffix;
    }

    public void setAttachSuffix(String attachSuffix) {
        this.attachSuffix = attachSuffix;
    }

    public Date getAttachCreated() {
        return attachCreated;
    }

    public void setAttachCreated(Date attachCreated) {
        this.attachCreated = attachCreated;
    }

    public String getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize;
    }

    public String getAttachWh() {
        return attachWh;
    }

    public void setAttachWh(String attachWh) {
        this.attachWh = attachWh;
    }

    public String getAttachLocation() {
        return attachLocation;
    }

    public void setAttachLocation(String attachLocation) {
        this.attachLocation = attachLocation;
    }

    public Integer getAttachOrigin() {
        return attachOrigin;
    }

    public void setAttachOrigin(Integer attachOrigin) {
        this.attachOrigin = attachOrigin;
    }   
}