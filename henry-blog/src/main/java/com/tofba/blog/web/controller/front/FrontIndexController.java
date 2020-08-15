package com.tofba.blog.web.controller.front;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.tofba.blog.model.domain.Post;
import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.model.enums.BlogPropertiesEnum;
import com.tofba.blog.service.PostService;
import com.tofba.blog.web.controller.core.BaseController;

import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <pre>
 * 前台首页控制器
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/4/26
 */
@Controller
@RequestMapping(value = {"/", "index"})
public class FrontIndexController extends BaseController {
    
    @Autowired
    private PostService postService;
    
    /**
     * 请求首页
     *
     * @param model model
     * @return 模板路径
     */
    @GetMapping
    public String index(Model model) {
        // 调用方法渲染首页
        return this.index(model, 1);
    }
    
    /**
     * 首页分页
     *
     * @param model model
     * @param page 当前页码
     * @param size 每页数量
     * @return 模板路径/themes/{theme}/index
     */
    @GetMapping(value = "page/{page}")
    public String index(Model model, @PathVariable(value = "page") Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "postDate");
        // 默认显示10条
        int size = 10;
        // 尝试加载设置选项，用于设置显示条数
        if (StrUtil.isNotBlank(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()))) {
            size = Integer.parseInt(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()));
        }
        // 所有文章数据，分页
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Post> posts = postService.findPostByStatus(pageable);
        if (null == posts) {
            return this.renderNotFound();
        }
        int[] rainbow = PageUtil.rainbow(page, posts.getTotalPages(), 3);
        model.addAttribute("is_index", true);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);
        return this.render("index");
    }
    
    /**
     * 搜索文章
     *
     * @param keyword keyword
     * @param model model
     * @return 模板路径/themes/{theme}/index
     */
    @GetMapping(value = "search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        Page<Post> posts = postService.searchByKeywords(keyword, null);
        model.addAttribute("posts", posts);
        return this.render("index");
    }
}