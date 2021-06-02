package com.example.uploadingfiles.repositories;

import com.example.uploadingfiles.model.Reaction;
import com.example.uploadingfiles.model.ReactionType;
import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    Reaction findByUserAndVideoinfo(User user, VideoInfo videoinfo);

    @Transactional
    @Modifying
    @Query("update Reaction r set r.reactiontype=:reactiontype where r.user= :user and r.videoinfo=:videoinfo")
    int setVideoLiked(User user, VideoInfo videoinfo, ReactionType reactiontype);
}
