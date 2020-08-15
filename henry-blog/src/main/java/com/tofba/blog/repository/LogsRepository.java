package com.tofba.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tofba.blog.model.domain.Logs;

/**
 * 日志持久层
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface LogsRepository extends JpaRepository<Logs, Long> {
    
    /**
     * 查询最新的五条数据
     *
     * @return List
     */
    @Query(value = "SELECT * FROM halo_logs ORDER BY log_created DESC LIMIT 5", nativeQuery = true)
    List<Logs> findTopFive();
}
