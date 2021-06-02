package com.example.uploadingfiles.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "videoinfo_id", nullable = false)
    private VideoInfo videoinfo;

    @Column(name = "reactionType")
    private ReactionType reactiontype;

    public Reaction() {
    }

    public Reaction(User user, VideoInfo videoInfo, ReactionType reactiontype) {
        this.user = user;
        this.videoinfo = videoInfo;
        this.reactiontype = reactiontype;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VideoInfo getVideoInfo() {
        return videoinfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoinfo = videoInfo;
    }

    public ReactionType getReactiontype() {
        return reactiontype;
    }

    public void setReactiontype(ReactionType reactionType) {
        this.reactiontype = reactionType;
    }
}
