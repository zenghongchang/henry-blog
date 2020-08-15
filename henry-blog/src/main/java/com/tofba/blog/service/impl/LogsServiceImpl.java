package com.tofba.blog.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tofba.blog.model.domain.Logs;
import com.tofba.blog.repository.LogsRepository;
import com.tofba.blog.service.LogsService;

import cn.hutool.extra.servlet.ServletUtil;


@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    private LogsRepository logsRepository;

    /**
     * 保存日志
     *
     * @param logTitle   logTitle
     * @param logContent logContent
     * @param request    request
     */
    @Override
    public void save(String logTitle, String logContent, HttpServletRequest request) {
        Logs logs = new Logs();
        logs.setLogTitle(logTitle);
        logs.setLogContent(logContent);
        logs.setLogCreated(new Date());
        logs.setLogIp(ServletUtil.getClientIP(request));
        logsRepository.save(logs);
    }

    /**
     * 移除所有日志
     */
    @Override
    public void removeAll() {
        logsRepository.deleteAll();
    }

    /**
     * 查询所有日志并分页
     *
     * @param pageable pageable
     * @return Page
     */
    @Override
    public Page<Logs> findAll(Pageable pageable) {
        return logsRepository.findAll(pageable);
    }

    /**
     * 查询最新的五条日志
     *
     * @return List
     */
    @Override
    public List<Logs> findLogsLatest() {
        return logsRepository.findTopFive();
    }
}
