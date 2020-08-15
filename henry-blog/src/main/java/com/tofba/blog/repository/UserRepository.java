package com.tofba.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tofba.blog.model.domain.User;

/**
 * <pre>
 * 用户持久层
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2017/11/14
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 根据用户名和密码查询
     *
     * @param userName userName
     * @param userPass userPass
     * @return User
     */
    User findByUserNameAndUserPass(String userName, String userPass);
    
    /**
     * 根据邮箱和密码查询
     *
     * @param userEmail userEmail
     * @param userPass userPass
     * @return User
     */
    User findByUserEmailAndUserPass(String userEmail, String userPass);
    
    /**
     * 根据用户编号和密码查询
     *
     * @param userId userId
     * @param userPass userpass
     * @return User
     */
    User findByUserIdAndUserPass(Long userId, String userPass);
}