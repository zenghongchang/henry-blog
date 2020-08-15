package com.tofba.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.tofba.blog.model.domain.Category;
import com.tofba.blog.repository.CategoryRepository;
import com.tofba.blog.service.CategoryService;

/**
 * <pre>
 * 分类业务逻辑实现类
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2017/11/30
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    private static final String POSTS_CACHE_NAME = "posts";
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    /**
     * 保存/修改分类目录
     *
     * @param category 分类目录
     * @return Category
     */
    @Override
    @CacheEvict(value = POSTS_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    
    /**
     * 根据编号移除分类目录
     *
     * @param cateId 分类目录编号
     * @return Category
     */
    @Override
    @CacheEvict(value = POSTS_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Category remove(Long cateId) {
        Optional<Category> category = this.findByCateId(cateId);
        categoryRepository.delete(category.get());
        return category.get();
    }
    
    /**
     * 查询所有分类目录
     *
     * @return List
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    /**
     * 根据编号查询分类目录
     *
     * @param cateId 分类编号
     * @return Category
     */
    @Override
    public Optional<Category> findByCateId(Long cateId) {
        return categoryRepository.findById(cateId);
    }
    
    /**
     * 根据分类目录路径查询，用于验证是否已经存在该路径
     *
     * @param cateUrl cateUrl
     * @return Category
     */
    @Override
    public Category findByCateUrl(String cateUrl) {
        return categoryRepository.findCategoryByCateUrl(cateUrl);
    }
    
    /**
     * 根据分类名称查询
     *
     * @param cateName 分类名称
     * @return Category
     */
    @Override
    public Category findByCateName(String cateName) {
        return categoryRepository.findCategoryByCateName(cateName);
    }
    
    /**
     * 将分类字符串集合转化为Category泛型集合
     *
     * @param strings strings
     * @return List
     */
    @Override
    public List<Category> strListToCateList(List<String> strings) {
        if (null == strings) {
            return null;
        }
        List<Category> categories = new ArrayList<Category>();
        Optional<Category> category = null;
        for (String str : strings) {
            category = findByCateId(Long.parseLong(str));
            categories.add(category.get());
        }
        return categories;
    }
}