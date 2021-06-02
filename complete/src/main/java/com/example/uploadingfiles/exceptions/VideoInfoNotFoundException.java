package com.example.uploadingfiles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="VideoInfo Not Found") //404
public class VideoInfoNotFoundException extends Exception {

    public VideoInfoNotFoundException(String link){
        super("VideoInfoNotFoundException with link="+link);
    }
}