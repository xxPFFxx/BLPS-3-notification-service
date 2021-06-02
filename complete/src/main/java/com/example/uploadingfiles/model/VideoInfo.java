package com.example.uploadingfiles.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "videoinfo")
public class VideoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="link")
    private String link;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String desc;
    @Column(name = "category")
    private String category;
    @Column(name = "releasetime")
    private String releasetime;
    @Column(name = "releasedate")
    private String releasedate;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public VideoInfo(String name, String desc, String category, String releasetime, String releasedate, String link, User user) {
        this.link = link;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.releasetime = releasetime;
        this.releasedate = releasedate;
        this.user = user;
    }

    public VideoInfo(String name, String desc, String category, String releasetime, String releasedate, String link) {
        this.link = link;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.releasetime = releasetime;
        this.releasedate = releasedate;
    }

    public VideoInfo() {

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releaseTime) {
        this.releasetime = releaseTime;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releaseDate) {
        this.releasedate = releaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
