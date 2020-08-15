package com.tofba.blog.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tofba.blog.model.domain.Category;
import com.tofba.blog.model.domain.Post;
import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.BlogPropertiesEnum;
import com.tofba.blog.model.enums.ResponseStatusEnum;
import com.tofba.blog.service.CategoryService;
import com.tofba.blog.service.PostService;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories")
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;



    /**
     * 获取所有分类
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": [
     *         {
     *             "cateId": "",
     *             "cateName": "",
     *             "cateUrl": "",
     *             "cateDesc": ""
     *             "count":"文章总数"
     *         }
     *     ]
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping
    public JsonResult categories() {
        List<JSONObject> list=new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        for(Category i:categories){
            JSONObject info=new JSONObject();
            info.put("cateId",i.getCateId());
            info.put("count",i.getPosts().size());
            info.put("cateName",i.getCateName());
            info.put("cateUrl",i.getCateDesc());
            info.put("categories",i.getCateDesc());
            list.add(info);

        }
        return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), list);



    }

    /**
     * 获取单个分类的信息
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": {
     *         "cateId": "",
     *         "cateName": "",
     *         "cateUrl": "",
     *         "cateDesc": ""
     *     }
     * }
     *     </pre>
     * </p>
     *
     * @param cateUrl 分类路径
     * @return JsonResult
     */
    @GetMapping(value = "/{cateUrl}")
    public JsonResult categories(@PathVariable("cateUrl") String cateUrl) {
        Category category = categoryService.findByCateUrl(cateUrl);
        if (null != category) {
            return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), category);
        } else {
            return new JsonResult(ResponseStatusEnum.EMPTY.getCode(), ResponseStatusEnum.EMPTY.getMsg());
        }
    }

    /**
     * 根据分类目录查询所有文章 分页
     * @param cateUrl 分类目录路径
     * @param page    页码
     * @return String
     */
    @GetMapping("{cateUrl}/page/{page}")
    public JsonResult categories(Model model,
                                 @PathVariable("cateUrl") String cateUrl,
                                 @PathVariable("page") Integer page) {
        Category category = categoryService.findByCateUrl(cateUrl);
        if (null != category) {
            Sort sort = new Sort(Sort.Direction.DESC, "postDate");
            Integer size = 10;
            if (StrUtil.isNotBlank(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()))) {
                size = Integer.parseInt(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()));
            }
            Pageable pageable = PageRequest.of(page - 1, size, sort);
            Page<Post> posts = postService.findPostByCategories(category, pageable);
            return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(),posts);
        }else{
            return new JsonResult(ResponseStatusEnum.NOTFOUND.getCode(), ResponseStatusEnum.NOTFOUND.getMsg());
        }
    }
}
