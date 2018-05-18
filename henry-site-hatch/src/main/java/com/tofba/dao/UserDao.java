package com.tofba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tofba.model.UserDomain;

/**
 * 用户实现Dao
 * 
 * @author Henry(fba02)
 * @Mapper注解与@MapperScan存在一个即可
 * @version [版本号, 2018年5月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserDao {
    
    /**
     * 更改用户信息
     * 
     * @param user
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月16日]
     * @see [类、类#方法、类#成员]
     */
    int updateUserInfo(UserDomain user);
    
    /**
     * 根据主键编号获取用户信息
     * 
     * @param uId
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月16日]
     * @see [类、类#方法、类#成员]
     */
    UserDomain getUserInfoById(@Param("uid") Integer uId);
    
    /**
     * 用户校验
     * 
     * @param username
     * @param password
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月16日]
     * @see [类、类#方法、类#成员]
     */
    UserDomain validationUser(@Param("username") String username, @Param("password") String password);
    
    @Select("SELECT * FROM t_users WHERE username = #{username}")
    UserDomain findByUserName(@Param("username") String username);
}