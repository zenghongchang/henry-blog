package com.tofba.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tofba.blog.model.domain.Logs;

public interface LogsService {

    /**
     * 保存日志
     *
     * @param logTitle   logTitle
     * @param logContent logContent
     * @param request    request
     */
    void save(String logTitle, String logContent, HttpServletRequest request);

    /**
     * 移除所有日志
     */
    void removeAll();

    /**
     * 查询所有日志并分页
     *
     * @param pageable pageable
     * @return Page
     */
    Page<Logs> findAll(Pageable pageable);

    /**
     * 查询最新的五条日志
     *
     * @return List
     */
    List<Logs> findLogsLatest();
}
