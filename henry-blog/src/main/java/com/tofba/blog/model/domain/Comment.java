package com.tofba.blog.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 评论
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "halo_comment")
public class Comment implements Serializable {
    
    /**
     * 评论id 自增
     */
    @Id
    @GeneratedValue
    private Long commentId;
    
    /**
     * 评论文章
     */
    @ManyToOne(targetEntity = Post.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;
    
    /**
     * 评论人
     */
    @NotBlank(message = "评论用户名不能为空")
    private String commentAuthor;
    
    /**
     * 评论人的邮箱
     */
    @Email(message = "邮箱格式不正确")
    @JsonIgnore
    private String commentAuthorEmail;
    
    /**
     * 评论人的主页
     */
    private String commentAuthorUrl;
    
    /**
     * 评论人的ip
     */
    @JsonIgnore
    private String commentAuthorIp;
    
    /**
     * 评论人的头像，用于gavatar
     */
    private String commentAuthorAvatarMd5;
    
    /**
     * 评论时间
     */
    private Date commentDate;
    
    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @Lob
    private String commentContent;
    
    /**
     * 评论者ua信息
     */
    @Column(length = 512)
    private String commentAgent;
    
    /**
     * 上一级
     */
    private Long commentParent = 0L;
    
    /**
     * 评论状态，0：正常，1：待审核，2：回收站
     */
    private Integer commentStatus = 1;
    
    /**
     * 是否是博主的评论 0:不是 1:是
     */
    private Integer isAdmin;
    
    /**
     * 当前评论下的所有子评论
     */
    @Transient
    private List<Comment> childComments;
    
    public Long getCommentId() {
        return commentId;
    }
    
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
    public Post getPost() {
        return post;
    }
    
    public void setPost(Post post) {
        this.post = post;
    }
    
    public String getCommentAuthor() {
        return commentAuthor;
    }
    
    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }
    
    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }
    
    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail;
    }
    
    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }
    
    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl;
    }
    
    public String getCommentAuthorIp() {
        return commentAuthorIp;
    }
    
    public void setCommentAuthorIp(String commentAuthorIp) {
        this.commentAuthorIp = commentAuthorIp;
    }
    
    public String getCommentAuthorAvatarMd5() {
        return commentAuthorAvatarMd5;
    }
    
    public void setCommentAuthorAvatarMd5(String commentAuthorAvatarMd5) {
        this.commentAuthorAvatarMd5 = commentAuthorAvatarMd5;
    }
    
    public Date getCommentDate() {
        return commentDate;
    }
    
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    
    public String getCommentContent() {
        return commentContent;
    }
    
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
    
    public String getCommentAgent() {
        return commentAgent;
    }
    
    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
    }
    
    public Long getCommentParent() {
        return commentParent;
    }
    
    public void setCommentParent(Long commentParent) {
        this.commentParent = commentParent;
    }
    
    public Integer getCommentStatus() {
        return commentStatus;
    }
    
    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }
    
    public Integer getIsAdmin() {
        return isAdmin;
    }
    
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public List<Comment> getChildComments() {
        return childComments;
    }
    
    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }
}