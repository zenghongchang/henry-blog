package com.tofba.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tofba.blog.model.domain.Gallery;

/**
 * <pre>
 *     图库持久层
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/2/26
 */
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
