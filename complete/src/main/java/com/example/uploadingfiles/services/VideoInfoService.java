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

    public void saveVideoInfo(String videoName,String videoDesc,String category,String releaseTime,String releaseDate){
        VideoInfo videoInfo = new VideoInfo(videoName, videoDesc, category, releaseTime, releaseDate);
        videoInfoRepository.save(videoInfo);
    }
}
