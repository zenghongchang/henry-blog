/**
 * Copyright ToFBA Ecommerce Logistics LTD. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Shenzhen ToFBA Ecommerce Logistics Co., Ltd.
 * No body can copy or modify any part of this source without the permission of
 * Shenzhen ToFBA Ecommerce Logistics Co., Ltd.
 *   _________    ___   ________  ______        _       
 *  |  _   _  | .'   `.|_   __  ||_   _ \      / \      
 *  |_/ | | \_|/  .-.  \ | |_ \_|  | |_) |    / _ \     
 *      | |    | |   | | |  _|     |  __'.   / ___ \    
 *     _| |_   \  `-'  /_| |_     _| |__) |_/ /   \ \_  
 *    |_____|   `.___.'|_____|   |_______/|____| |____|
 *                                                
 */
package com.tofba.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

/**
 * dataSource配置
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@MapperScan("cn.tofba.dao")
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    private Environment env;
    
    @Autowired
    private PageInterceptor pageInterceptor;
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
        throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        // 该配置非常的重要，如果不将PageInterceptor设置到SqlSessionFactoryBean中，导致分页失效
        fb.setPlugins(new Interceptor[] {pageInterceptor});
        fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return fb.getObject();
    }
}