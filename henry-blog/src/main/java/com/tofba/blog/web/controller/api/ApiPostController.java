package com.tofba.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tofba.blog.model.domain.Post;
import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.BlogPropertiesEnum;
import com.tofba.blog.model.enums.PostStatusEnum;
import com.tofba.blog.model.enums.PostTypeEnum;
import com.tofba.blog.model.enums.ResponseStatusEnum;
import com.tofba.blog.service.PostService;

import cn.hutool.core.util.StrUtil;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts")
public class ApiPostController {

    @Autowired
    private PostService postService;

    /**
     * 获取文章列表 分页
     *
     * <p>
     *     result api
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": {
     *         "content": [
     *             {
     *                 "postId": ,
     *                 "user": {},
     *                 "postTitle": "",
     *                 "postType": "",
     *                 "postContentMd": "",
     *                 "postContent": "",
     *                 "postUrl": "",
     *                 "postSummary": "",
     *                 "categories": [],
     *                 "tags": [],
     *                 "comments": [],
     *                 "postThumbnail": "",
     *                 "postDate": "",
     *                 "postUpdate": "",
     *                 "postStatus": 0,
     *                 "postViews": 0,
     *                 "allowComment": 1,
     *                 "customTpl": ""
     *             }
     *         ],
     *         "pageable": {
     *             "sort": {
     *                 "sorted": true,
     *                 "unsorted": false,
     *                 "empty": false
     *             },
     *             "offset": 0,
     *             "pageSize": 10,
     *             "pageNumber": 0,
     *             "unpaged": false,
     *             "paged": true
     *         },
     *         "last": true,
     *         "totalElements": 1,
     *         "totalPages": 1,
     *         "size": 10,
     *         "number": 0,
     *         "first": true,
     *         "numberOfElements": 1,
     *         "sort": {
     *             "sorted": true,
     *             "unsorted": false,
     *             "empty": false
     *         },
     *         "empty": false
     *     }
     * }
     *     </pre>
     * </p>
     *
     * @param page 页码
     * @return JsonResult
     */
    @GetMapping(value = "/page/{page}")
    public JsonResult posts(@PathVariable(value = "page") Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "postDate");
        int size = 10;
        if (StrUtil.isNotBlank(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()))) {
            size = Integer.parseInt(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()));
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Post> posts = postService.findPostByStatus(PostStatusEnum.PUBLISHED.getCode(), PostTypeEnum.POST_TYPE_POST.getDesc(), pageable);
        if (null == posts) {
            return new JsonResult(ResponseStatusEnum.EMPTY.getCode(), ResponseStatusEnum.EMPTY.getMsg());
        }
        return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), posts);
    }

    /**
     * 搜索文章
     * @param page 页码
     * @param keyword keyword
     */
    @GetMapping(value = "search")
    public JsonResult search(@RequestParam("keyword") String keyword, @PathVariable(value = "page") Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "postDate");
        int size = 10;
        if (StrUtil.isNotBlank(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()))) {
            size = Integer.parseInt(BlogConstant.OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()));
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Post> posts = postService.searchByKeywords(keyword, pageable);
        if (null == posts) {
            return new JsonResult(ResponseStatusEnum.EMPTY.getCode(), ResponseStatusEnum.EMPTY.getMsg());
        }
        return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), posts);
    }
    /**
     * 获取单个文章信息
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": {
     *         "postId": ,
     *         "user": {},
     *         "postTitle": "",
     *         "postType": "",
     *         "postContentMd": "",
     *         "postContent": "",
     *         "postUrl": "",
     *         "postSummary": "",
     *         "categories": [],
     *         "tags": [],
     *         "comments": [],
     *         "postThumbnail": "",
     *         "postDate": "",
     *         "postUpdate": "",
     *         "postStatus": 0,
     *         "postViews": 0,
     *         "allowComment": 1,
     *         "customTpl": ""
     *     }
     * }
     *     </pre>
     * </p>
     *
     * @param postId 文章编号
     * @return JsonResult
     */
    @GetMapping(value = "/{postId}")
    public JsonResult posts(@PathVariable(value = "postId") Long postId) {
        Post post = postService.findByPostId(postId, PostTypeEnum.POST_TYPE_POST.getDesc());
        if (null != post) {

            postService.cacheViews(post.getPostId());
            return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), post);
        } else {
            return new JsonResult(ResponseStatusEnum.NOTFOUND.getCode(), ResponseStatusEnum.NOTFOUND.getMsg());
        }
    }
}
