package com.tofba.blog.model.tag;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tofba.blog.service.CategoryService;
import com.tofba.blog.service.CommentService;
import com.tofba.blog.service.LinkService;
import com.tofba.blog.service.MenuService;
import com.tofba.blog.service.TagService;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * <pre>
 * FreeMarker自定义标签
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/4/26
 */
@Component
public class CommonTagDirective implements TemplateDirectiveModel {    
    private static final String METHOD_KEY = "method";    
    @Autowired
    private MenuService menuService;    
    @Autowired
    private CategoryService categoryService;    
    @Autowired
    private CommentService commentService;    
    @Autowired
    private TagService tagService;    
    @Autowired
    private LinkService linkService;
    
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
        throws TemplateException, IOException {
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        if (map.containsKey(METHOD_KEY)) {
            String method = map.get(METHOD_KEY).toString();
            switch (method) {
                case "menus":
                    environment.setVariable("menus", builder.build().wrap(menuService.findAll()));
                    break;
                case "categories":
                    environment.setVariable("categories", builder.build().wrap(categoryService.findAll()));
                    break;
                case "tags":
                    environment.setVariable("tags", builder.build().wrap(tagService.findAll()));
                    break;
                case "links":
                    environment.setVariable("links", builder.build().wrap(linkService.findAll()));
                    break;
                case "newComments":
                    environment.setVariable("newComments", builder.build().wrap(commentService.findAll(1)));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }
}