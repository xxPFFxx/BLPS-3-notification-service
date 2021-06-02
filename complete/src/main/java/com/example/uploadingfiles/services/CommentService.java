package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.Comment;
import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.CommentRepository;
import com.example.uploadingfiles.repositories.UserRepository;
import com.example.uploadingfiles.repositories.VideoInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final VideoInfoRepository videoInfoRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, VideoInfoRepository videoInfoRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.videoInfoRepository = videoInfoRepository;
    }

    public Comment addComment(String text, String link, String username){
        User user = userRepository.findByUsername(username);
        VideoInfo videoInfo = videoInfoRepository.findVideoInfoByLink(link);
        Comment comment = new Comment(user, videoInfo, text);
        return commentRepository.save(comment);
    }
}
