package com.tofba.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tofba.blog.model.domain.Menu;
import com.tofba.blog.repository.MenuRepository;
import com.tofba.blog.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {    
    private static final String MENUS_CACHE_KEY = "'menu'";    
    private static final String MENUS_CACHE_NAME = "menus";    
    @Autowired
    private MenuRepository menuRepository;
    
    /**
     * 查询所有菜单
     *
     * @return List
     */
    @Override
    @Cacheable(value = MENUS_CACHE_NAME, key = MENUS_CACHE_KEY)
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
    
    /**
     * 新增/修改菜单
     *
     * @param menu menu
     * @return Menu
     */
    @Override
    @CacheEvict(value = MENUS_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }
    
    /**
     * 删除菜单
     *
     * @param menuId menuId
     * @return Menu
     */
    @Override
    @CacheEvict(value = MENUS_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Menu remove(Long menuId) {
        Optional<Menu> menu = this.findByMenuId(menuId);
        menuRepository.delete(menu.get());
        return menu.get();
    }
    
    /**
     * 根据编号查询菜单
     *
     * @param menuId menuId
     * @return Menu
     */
    @Override
    public Optional<Menu> findByMenuId(Long menuId) {
        return menuRepository.findById(menuId);
    }
}
