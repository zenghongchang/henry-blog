package com.tofba.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tofba.blog.model.domain.Attachment;

/**
 * 附件持久层
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
