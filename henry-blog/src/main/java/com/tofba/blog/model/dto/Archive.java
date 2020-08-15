package com.tofba.blog.model.dto;

import java.io.Serializable;
import java.util.List;

import com.tofba.blog.model.domain.Post;

/**
 * 文章归档
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年7月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
public class Archive implements Serializable {
    /**
     * 年份
     */
    private String year;
    
    /**
     * 月份
     */
    private String month;
    
    /**
     * 对应的文章数
     */
    private String count;
    
    /**
     * 对应的文章
     */
    private List<Post> posts;
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getMonth() {
        return month;
    }
    
    public void setMonth(String month) {
        this.month = month;
    }
    
    public String getCount() {
        return count;
    }
    
    public void setCount(String count) {
        this.count = count;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
