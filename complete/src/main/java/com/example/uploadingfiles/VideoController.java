package com.example.uploadingfiles;

import com.example.uploadingfiles.services.VideoInfoService;
import com.example.uploadingfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class VideoController {

    private VideoInfoService videoInfoService;
    private StorageService storageService;

    @Autowired
    public void FileUploadController(StorageService storageService, VideoInfoService videoInfoService) {
        this.storageService = storageService;
        this.videoInfoService = videoInfoService;
    }

    // http://localhost:8080/addVideoInfo?videoName=hi&videoDesc=desc&category=cat&releaseTime=timeee&releaseDate=2021-03-03



    @PostMapping(value = "/addVideoInfo", produces = "application/json")
    public String uploadVideoInfo(@RequestParam String videoName, @RequestParam String videoDesc,
                                  @RequestParam String category, @RequestParam String releaseTime,
                                  @RequestParam String releaseDate, RedirectAttributes redirectAttributes,
                                  Model model){
        System.out.println("I CAUGHT SOMETHING! MAYBE IT'S A REQUEST!!!!");
        System.out.println(videoName);
        System.out.println(videoDesc);
        System.out.println(category);
        System.out.println(releaseTime);
        System.out.println(releaseDate);


        videoInfoService.saveVideoInfo(videoName, videoDesc, category, releaseTime, releaseDate);
        if (releaseTime.equals("")){
//            redirectAttributes.addFlashAttribute("message",
//                    "Видео "  + videoName + " загружено");
//            model.addAttribute("message", );
            return "Видео "  + videoName + " загружено";
        }
        else {
//            redirectAttributes.addFlashAttribute("message",
//                    "Видео "  + videoName + " будет опубликовано " + releaseDate + " в " + releaseTime);
//            model.addAttribute("message", "Видео "  + videoName + " будет опубликовано " + releaseDate + " в " + releaseTime);
            return "Видео "  + videoName + " будет опубликовано " + releaseDate + " в " + releaseTime;
        }

    }
}
