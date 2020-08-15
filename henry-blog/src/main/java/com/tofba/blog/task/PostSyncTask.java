package com.tofba.blog.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tofba.blog.model.domain.Post;
import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.service.PostService;
import com.tofba.blog.utils.CalendarRegistry;

public class PostSyncTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 将缓存的图文浏览数写入数据库
     * 
     * @author Henry(fba02)
     * @version [版本号, 2020年7月25日]
     * @see [类、类#方法、类#成员]
     */
    public void postSync() {
        PostService postService = CalendarRegistry.getBean(PostService.class);
        Post post = null;
        int count = 0;
        for (Long key : BlogConstant.POSTS_VIEWS.keySet()) {
            post = postService.findByPostId(key).orElse(null);
            if (null != post) {
                post.setPostViews(post.getPostViews() + BlogConstant.POSTS_VIEWS.get(key));
                postService.save(post);
                count++;
            }
        }
        logger.info("The number of visits to {} posts has been updated", count);
        BlogConstant.POSTS_VIEWS.clear();
    }
}