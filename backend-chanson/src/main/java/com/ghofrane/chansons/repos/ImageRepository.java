package com.ghofrane.chansons.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ghofrane.chansons.entities.Image;
import jakarta.transaction.Transactional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Transactional
    void deleteByChansonIdChanson(Long chansonId);
}