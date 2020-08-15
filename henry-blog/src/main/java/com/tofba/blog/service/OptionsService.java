package com.tofba.blog.service;

import java.util.Map;

import com.tofba.blog.model.domain.Options;

public interface OptionsService {

    /**
     * 保存单个设置选项
     *
     * @param key   key
     * @param value value
     */
    void saveOption(String key, String value);

    /**
     * 保存多个设置选项
     *
     * @param options options
     */
    void saveOptions(Map<String, String> options);

    /**
     * 移除设置选项
     *
     * @param options options
     */
    void removeOption(Options options);

    /**
     * 获取所有设置选项
     *
     * @return Map
     */
    Map<String, String> findAllOptions();

    /**
     * 根据key查询单个设置
     *
     * @param key key
     * @return String
     */
    String findOneOption(String key);
}
