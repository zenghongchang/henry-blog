package com.tofba.blog.model.dto;

import java.util.Date;

/**
 * 备份信息
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BackupDto {
    
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 创建时间
     */
    private Date createAt;
    
    /**
     * 文件大小
     */
    private String fileSize;
    
    /**
     * 文件类型
     */
    private String fileType;
    
    /**
     * 备份类型
     */
    private String backupType;
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public Date getCreateAt() {
        return createAt;
    }
    
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    
    public String getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public String getBackupType() {
        return backupType;
    }
    
    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }    
}