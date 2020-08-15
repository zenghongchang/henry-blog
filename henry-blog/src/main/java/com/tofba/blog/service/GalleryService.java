package com.tofba.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tofba.blog.model.domain.Gallery;

public interface GalleryService {

    /**
     * 保存图片
     *
     * @param gallery gallery
     * @return Gallery
     */
    Gallery save(Gallery gallery);

    /**
     * 根据编号删除图片
     *
     * @param galleryId galleryId
     * @return Gallery
     */
    Gallery remove(Long galleryId);

    /**
     * 查询所有图片 分页
     *
     * @param pageable pageable
     * @return Page
     */
    Page<Gallery> findAll(Pageable pageable);

    /**
     * 查询所有图片 不分页
     *
     * @return List
     */
    List<Gallery> findAll();

    /**
     * 根据编号查询图片信息
     *
     * @param galleryId galleryId
     * @return Optional
     */
    Optional<Gallery> findByGalleryId(Long galleryId);
}
