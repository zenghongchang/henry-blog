package com.tofba.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tofba.blog.model.domain.Category;

/**
 * 分类持久层
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * 根据分类目录路径查询，用于验证是否已经存在该路径
     *
     * @param cateUrl cateUrl 文章url
     * @return Category
     */
    Category findCategoryByCateUrl(String cateUrl);
    
    /**
     * 根据分类名称查询
     *
     * @param cateName 分类名称
     * @return Category
     */
    Category findCategoryByCateName(String cateName);
    
    /**
     * 根据分类查询文章总数
     *
     * @return Long
     */
    @Query(value = "select count(1) from halo_posts_categories where cate_id=:cateId", nativeQuery = true)
    Long getCategoryPostCount(@Param("cateId") Long cateId);
}