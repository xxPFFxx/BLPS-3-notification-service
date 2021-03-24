package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.VideoInfoRepository;
import org.springframework.stereotype.Service;

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
        return videoInfoRepository.getVideoInfoByLink(link);
    }
}
