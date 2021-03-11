package com.example.uploadingfiles.controllers;

import com.example.uploadingfiles.model.VideoInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class VideoController {

    // http://localhost:8080/addVideoInfo?videoName=hi&videoDesc=desc&category=cat&releaseTime=timeee&releaseDate=2021-03-03

    @RequestMapping("/addVideoInfo")
    public String uploadVideoInfo(@RequestParam String videoName, @RequestParam String videoDesc,
                                  @RequestParam String category, @RequestParam String releaseTime,
                                  @RequestParam String releaseDate){
        System.out.println("I CAUGHT SOMETHING! MAYBE IT'S A REQUEST!!!!");
        System.out.println(videoName);
        System.out.println(videoDesc);
        System.out.println(category);
        System.out.println(releaseTime);
        System.out.println(releaseDate);

        //todo Вот здесь нужно добавить всё в PostgreSQL

        return "Got everything!" + videoName;

    }
}
