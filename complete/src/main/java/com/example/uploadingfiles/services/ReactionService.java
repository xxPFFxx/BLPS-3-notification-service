package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.Reaction;
import com.example.uploadingfiles.model.ReactionType;
import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.CommentRepository;
import com.example.uploadingfiles.repositories.ReactionRepository;
import com.example.uploadingfiles.repositories.UserRepository;
import com.example.uploadingfiles.repositories.VideoInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class ReactionService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final VideoInfoRepository videoInfoRepository;
    private final ReactionRepository reactionRepository;

    public ReactionService(CommentRepository commentRepository, UserRepository userRepository, VideoInfoRepository videoInfoRepository, ReactionRepository reactionRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.videoInfoRepository = videoInfoRepository;
        this.reactionRepository = reactionRepository;
    }

    public Reaction likeVideo(String username, String link){
        VideoInfo videoInfo = videoInfoRepository.findVideoInfoByLink(link);
        User user = userRepository.findByUsername(username);
        Reaction existingReaction = reactionRepository.findByUserAndVideoinfo(user, videoInfo);
        if (existingReaction == null) {
            Reaction reaction = new Reaction(user, videoInfo, ReactionType.LIKE);
            return reactionRepository.save(reaction);
        }
        else {
            if (existingReaction.getReactiontype() != ReactionType.LIKE) {
                reactionRepository.setVideoLiked(user, videoInfo, ReactionType.LIKE);
                existingReaction.setReactiontype(ReactionType.LIKE);
            }
            return existingReaction;
        }
    }
}
