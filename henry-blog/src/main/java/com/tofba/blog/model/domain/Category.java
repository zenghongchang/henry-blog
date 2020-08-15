package com.tofba.blog.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文章分类
 *
 *
 * @author  Henry(fba02)
 * @version  [版本号, 2020年8月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "halo_category")
public class Category implements Serializable {
    /**
     * 分类编号
     */
    @Id
    @GeneratedValue
    private Long cateId;
    
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String cateName;
    
    /**
     * 分类路径
     */
    @NotBlank(message = "分类路径不能为空")
    private String cateUrl;
    
    /**
     * 分类描述
     */
    private String cateDesc;
    
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Post> posts = new ArrayList<Post>();
    
    public Long getCateId() {
        return cateId;
    }
    
    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }
    
    public String getCateName() {
        return cateName;
    }
    
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
    public String getCateUrl() {
        return cateUrl;
    }
    
    public void setCateUrl(String cateUrl) {
        this.cateUrl = cateUrl;
    }
    
    public String getCateDesc() {
        return cateDesc;
    }
    
    public void setCateDesc(String cateDesc) {
        this.cateDesc = cateDesc;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}