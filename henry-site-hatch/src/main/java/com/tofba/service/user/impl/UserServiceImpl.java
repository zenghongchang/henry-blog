package com.tofba.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tofba.common.exception.UtilException;
import com.tofba.common.util.MD5Util;
import com.tofba.dao.UserDao;
import com.tofba.model.UserDomain;
import com.tofba.service.user.UserService;

/**
 * 用户相关实现
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    @Transactional
    @Override
    public int updateUserInfo(UserDomain user) {
        if (null == user)
            throw new UtilException("用户信息不能为空");
        return userDao.updateUserInfo(user);
    }
    
    @Override
    public UserDomain getUserInfoById(Integer uId) {
        return userDao.getUserInfoById(uId);
    }
    
    @Override
    public UserDomain validationUser(String username, String password) {
        String pwd = new MD5Util(password).toMD5();
        UserDomain user = userDao.validationUser(username, pwd);
        return user;
    }
    
    @Override
    public UserDomain findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}