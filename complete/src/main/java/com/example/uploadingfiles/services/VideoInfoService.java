package com.example.uploadingfiles.services;

import com.example.uploadingfiles.repositories.VideoInfoRepository;

public class VideoInfoService {
    private final VideoInfoRepository videoInfoRepository;

    public VideoInfoService(VideoInfoRepository videoInfoRepository) {
        this.videoInfoRepository = videoInfoRepository;
    }
}
