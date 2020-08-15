package com.tofba.blog.config;

import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.tofba.blog.model.tag.ArticleTagDirective;
import com.tofba.blog.model.tag.CommonTagDirective;
import com.tofba.blog.service.OptionsService;
import com.tofba.blog.service.UserService;

import javax.annotation.PostConstruct;

/**
 * FreeMarker配置
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Configuration
public class FreeMarkerAutoConfiguration {
    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonTagDirective commonTagDirective;
    @Autowired
    private ArticleTagDirective articleTagDirective;

    @PostConstruct
    public void setSharedVariable() {
        try {
            // 自定义标签
            configuration.setSharedVariable("commonTag", commonTagDirective);
            configuration.setSharedVariable("articleTag", articleTagDirective);
            configuration.setSharedVariable("options", optionsService.findAllOptions());
            configuration.setSharedVariable("user", userService.findUser());
        } catch (TemplateModelException e) {
            log.error("Custom tags failed to load：{}", e.getMessage());
        }
    }
}