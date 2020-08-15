package com.tofba.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tofba.blog.service.CommentService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/comments")
public class ApiCommentController {    
    @Autowired
    private CommentService commentService;
}
