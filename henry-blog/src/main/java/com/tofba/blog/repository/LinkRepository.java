package com.tofba.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tofba.blog.model.domain.Link;

/**
 * <pre>
 *     友情链接持久层
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2017/11/14
 */
public interface LinkRepository extends JpaRepository<Link, Long> {
}
