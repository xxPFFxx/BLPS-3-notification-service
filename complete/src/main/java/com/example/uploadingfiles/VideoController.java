package com.example.uploadingfiles;

import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.services.VideoInfoService;
import com.example.uploadingfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class VideoController {

    private VideoInfoService videoInfoService;
    private StorageService storageService;

    @Autowired
    public void FileUploadController(StorageService storageService, VideoInfoService videoInfoService) {
        this.storageService = storageService;
        this.videoInfoService = videoInfoService;
    }

    // http://localhost:8080/addVideoInfo?videoName=hi&videoDesc=desc&category=cat&releaseTime=timeee&releaseDate=2021-03-03


    @GetMapping(value = "/getVideo", produces = "application/json")
    public VideoInfo getVideo(@RequestParam String link){
        if (videoInfoService.checkVideoInfo(link)){
            return videoInfoService.getVideo(link);
        }
        else {
            return new VideoInfo("Не найдено", "Не найдено", "Не найдено", "Не найдено", "Не найдено", link);
//            noVideoInfo(link);
        }
    }

    public Map<String, String> noVideoInfo(String link){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Не нашлось видео по ссылке " + link);
        return map;
    }
    @PostMapping(value = "/addVideoInfo", produces = "application/json")
    public Map<String, String>  uploadVideoInfo(@RequestParam String videoName, @RequestParam String videoDesc,
                                               @RequestParam String category, @RequestParam String releaseTime,
                                               @RequestParam String releaseDate, @RequestParam String link, RedirectAttributes redirectAttributes,
                                               Model model){
        System.out.println("I CAUGHT SOMETHING! MAYBE IT'S A REQUEST!!!!");
        System.out.println(videoName);
        System.out.println(videoDesc);
        System.out.println(category);
        System.out.println(releaseTime);
        System.out.println(releaseDate);
        HashMap<String, String> map = new HashMap<>();
        if (videoInfoService.checkVideoInfo(link)){
            videoInfoService.updateVideoInfo(videoName, videoDesc, category, releaseTime, releaseDate, link);
        }
        else {
            map.put("message", "Видео не найдено");
            return map;
        }
        //videoInfoService.saveVideoInfo(videoName, videoDesc, category, releaseTime, releaseDate, link);

        if (releaseTime.equals("")){
//            redirectAttributes.addFlashAttribute("message",
//                    "Видео "  + videoName + " загружено");
//            model.addAttribute("message", );
            map.put("message", "Видео "  + videoName + " загружено");
            return map;
            //return "Видео "  + videoName + " загружено";
        }
        else {
//            redirectAttributes.addFlashAttribute("message",
//                    "Видео "  + videoName + " будет опубликовано " + releaseDate + " в " + releaseTime);
//            model.addAttribute("message", "Видео "  + videoName + " будет опубликовано " + releaseDate + " в " + releaseTime);
            map.put("message", "Видео "  + videoName + " будет опубликовано " + releaseDate + " в " + releaseTime);
            return map;
            //return "Видео "  + videoName + " будет опубликовано " + releaseDate + " в " + releaseTime;
        }

    }
}
