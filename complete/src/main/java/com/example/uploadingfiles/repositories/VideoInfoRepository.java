package com.example.uploadingfiles.repositories;


import com.example.uploadingfiles.model.VideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoInfoRepository extends JpaRepository<VideoInfo, Long> {
}
