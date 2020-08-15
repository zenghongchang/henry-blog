package com.tofba.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tofba.blog.model.domain.Attachment;

/**
 * <pre>
 * 附件持久层
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/1/10
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
