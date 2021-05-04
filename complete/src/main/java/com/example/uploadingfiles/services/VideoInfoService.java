package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.VideoInfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VideoInfoService {


    private final VideoInfoRepository videoInfoRepository;

    public VideoInfoService(VideoInfoRepository videoInfoRepository) {
        this.videoInfoRepository = videoInfoRepository;
    }

    public void saveVideoInfo(String videoName,String videoDesc,String category,String releaseTime,String releaseDate, String link){
        VideoInfo videoInfo = new VideoInfo(videoName, videoDesc, category, releaseTime, releaseDate, link);
        videoInfoRepository.save(videoInfo);
    }

    public boolean checkVideoInfo(String link){
        return videoInfoRepository.countVideoInfosByLink(link) > 0;
    }

    public void updateVideoInfo(String videoName,String videoDesc,String category,String releaseTime,String releaseDate,String link){
        videoInfoRepository.updateVideoInfo(videoName, videoDesc, category, releaseTime, releaseDate, link);
    }

    public VideoInfo getVideo(String link){
        return videoInfoRepository.findVideoInfoByLink(link);
    }

    public int deleteVideo(String link){
        return videoInfoRepository.deleteVideoInfoByLink(link);
    }

    public int changeVideoName(String link, String videoName){
        return videoInfoRepository.renameVideoNameByLink(link, videoName);
    }

    public int changeVideoDesc(String link, String videoDesc){
        return videoInfoRepository.renameVideoDescByLink(link, videoDesc);
    }
}
