package com.tofba.blog.web.controller.front;

import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tofba.blog.model.domain.Category;
import com.tofba.blog.model.domain.Post;
import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.model.enums.BlogPropertiesEnum;
import com.tofba.blog.service.CategoryService;
import com.tofba.blog.service.PostService;
import com.tofba.blog.web.controller.core.BaseController;

import java.util.List;

/**
 * <pre>
 *     前台文章分类控制器
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/4/26
 */
@Controller
@RequestMapping(value = "categories")
public class FrontCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    /**
     * 分类列表页面
     *
     * @param model model
     * @return String
     */
    @GetMapping
    public String categories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return this.render("categories");
    }

    /**
     * 根据分类路径查询文章
     *
     * @param model   model
     * @param cateUrl cateUrl
     * @return string
     */
    @GetMapping(value = "{cateUrl}")
    public String categories(Model model,
                             @PathVariable("cateUrl") String cateUrl) {
        return this.categories(model, cateUrl, 1);
    }

    /**
     * 根据分类目录查询所有文章 分页
     *
     * @param model   model
     * @param cateUrl 分类目录路径
     * @param page    页码
     * @return String
     */
    @GetMapping("{cateUrl}/page/{page}")
    public String categories(Model model,
                             @PathVariable("cateUrl") String cateUrl,
                             @PathVariable("page") Integer page) {
        Category category = categoryService.findByCateUrl(cateUrl);
        if (null == category) {
            return this.renderNotFound();
        }
        Sort sort = new Sort(Sort.Direction.DESC, "postDate");
        Integer size = 10;
        if (StrUtil.isNotBlank(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()))) {
            size = Integer.parseInt(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()));
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Post> posts = postService.findPostByCategories(category, pageable);
        int[] rainbow = PageUtil.rainbow(page, posts.getTotalPages(), 3);
        model.addAttribute("is_categories",true);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);
        model.addAttribute("category", category);
        return this.render("category");
    }
}
