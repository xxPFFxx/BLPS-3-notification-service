package com.example.uploadingfiles;

import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.services.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class VideoController {

    @Autowired
    private VideoInfoService videoInfoService;

    // http://localhost:8080/addVideoInfo?videoName=hi&videoDesc=desc&category=cat&releaseTime=timeee&releaseDate=2021-03-03

    @PostMapping(value = "/addVideoInfo", produces = "application/json")
    public String uploadVideoInfo(@RequestParam String videoName, @RequestParam String videoDesc,
                                  @RequestParam String category, @RequestParam String releaseTime,
                                  @RequestParam String releaseDate){
        System.out.println("I CAUGHT SOMETHING! MAYBE IT'S A REQUEST!!!!");
        System.out.println(videoName);
        System.out.println(videoDesc);
        System.out.println(category);
        System.out.println(releaseTime);
        System.out.println(releaseDate);


        videoInfoService.saveVideoInfo(videoName, videoDesc, category, releaseTime, releaseDate);
        //todo Вот здесь нужно добавить всё в PostgreSQL

        return "Got everything!" + videoName;

    }
}
