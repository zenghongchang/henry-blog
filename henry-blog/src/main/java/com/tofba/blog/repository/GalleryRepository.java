package com.tofba.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tofba.blog.model.domain.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
