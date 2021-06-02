package com.example.uploadingfiles.repositories;


import com.example.uploadingfiles.model.VideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VideoInfoRepository extends JpaRepository<VideoInfo, Long> {

    long countVideoInfosByLink(String link);

    @Transactional
    @Modifying
    @Query("update VideoInfo v set v.name=:videoName, v.desc=:videoDesc, v.category=:category, v.releasetime=:releaseTime, v.releasedate=:releaseDate where v.link= :link")
    int updateVideoInfo(String videoName, String videoDesc, String category, String releaseTime, String releaseDate, String link);

    VideoInfo findVideoInfoByLink(String link);

    @Transactional
    int deleteVideoInfoByLink(String link);

    @Transactional
    @Modifying
    @Query("update VideoInfo v set v.name=:videoName where v.link= :link")
    int renameVideoNameByLink(String link, String videoName);

    @Transactional
    @Modifying
    @Query("update VideoInfo v set v.desc=:videoDesc where v.link= :link")
    int renameVideoDescByLink(String link, String videoDesc);

    @Transactional
    @Modifying
    @Query("update VideoInfo v set v.category=:videoCategory where v.link= :link")
    int renameVideoCategoryByLink(String link, String videoCategory);
}
