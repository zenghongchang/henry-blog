package com.tofba.blog.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文章标签
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "halo_tag")
public class Tag implements Serializable {
    /**
     * 标签编号
     */
    @Id
    @GeneratedValue
    private Long tagId;
    
    /**
     * 标签名称
     */
    private String tagName;
    
    /**
     * 标签路径
     */
    private String tagUrl;
    
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Post> posts = new ArrayList<Post>();
    
    public Long getTagId() {
        return tagId;
    }
    
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
    
    public String getTagName() {
        return tagName;
    }
    
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    public String getTagUrl() {
        return tagUrl;
    }
    
    public void setTagUrl(String tagUrl) {
        this.tagUrl = tagUrl;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }    
}