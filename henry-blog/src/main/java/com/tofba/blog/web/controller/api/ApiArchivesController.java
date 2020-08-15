package com.tofba.blog.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tofba.blog.model.dto.Archive;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.ResponseStatusEnum;
import com.tofba.blog.service.PostService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/archives")
public class ApiArchivesController {

    @Autowired
    private PostService postService;

    /**
     * 根据年份归档
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": [
     *         {
     *             "year": "",
     *             "month": "",
     *             "count": "",
     *             "posts": [
     *                 {
     *                     "postId": "",
     *                     "user": {},
     *                     "postTitle": "",
     *                     "postType": "",
     *                     "postContentMd": "",
     *                     "postContent": "",
     *                     "postUrl": "",
     *                     "postSummary": "",
     *                     "categories": [],
     *                     "tags": [],
     *                     "comments": [],
     *                     "postThumbnail": "",
     *                     "postDate": "",
     *                     "postUpdate": "",
     *                     "postStatus": 0,
     *                     "postViews": 0,
     *                     "allowComment": 1,
     *                     "customTpl": ""
     *                 }
     *             ]
     *         }
     *     ]
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping(value = "/year")
    public JsonResult archivesYear() {
        List<Archive> archives = postService.findPostGroupByYear();
        if (null != archives && archives.size() > 0) {
            return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), archives);
        } else {
            return new JsonResult(ResponseStatusEnum.EMPTY.getCode(), ResponseStatusEnum.EMPTY.getMsg());
        }
    }

    /**
     * 根据月份归档
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": [
     *         {
     *             "year": "",
     *             "month": "",
     *             "count": "",
     *             "posts": [
     *                 {
     *                     "postId": "",
     *                     "user": {},
     *                     "postTitle": "",
     *                     "postType": "",
     *                     "postContentMd": "",
     *                     "postContent": "",
     *                     "postUrl": "",
     *                     "postSummary": "",
     *                     "categories": [],
     *                     "tags": [],
     *                     "comments": [],
     *                     "postThumbnail": "",
     *                     "postDate": "",
     *                     "postUpdate": "",
     *                     "postStatus": 0,
     *                     "postViews": 0,
     *                     "allowComment": 1,
     *                     "customTpl": ""
     *                 }
     *             ]
     *         }
     *     ]
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping(value = "/year/month")
    public JsonResult archivesYearAndMonth() {
        List<Archive> archives = postService.findPostGroupByYearAndMonth();
        if (null != archives && archives.size() > 0) {
            return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), archives);
        } else {
            return new JsonResult(ResponseStatusEnum.EMPTY.getCode(), ResponseStatusEnum.EMPTY.getMsg());
        }
    }
}
