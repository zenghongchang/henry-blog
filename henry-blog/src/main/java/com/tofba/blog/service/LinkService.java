package com.tofba.blog.service;

import java.util.List;
import java.util.Optional;

import com.tofba.blog.model.domain.Link;

public interface LinkService {

    /**
     * 新增/修改友情链接
     *
     * @param link link
     * @return Link
     */
    Link save(Link link);

    /**
     * 根据编号删除
     *
     * @param linkId linkId
     * @return Link
     */
    Link remove(Long linkId);

    /**
     * 查询所有
     *
     * @return List
     */
    List<Link> findAll();

    /**
     * 根据编号查询单个链接
     *
     * @param linkId linkId
     * @return Link
     */
    Optional<Link> findByLinkId(Long linkId);
}
