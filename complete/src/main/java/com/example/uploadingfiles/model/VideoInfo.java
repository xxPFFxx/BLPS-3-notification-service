package com.example.uploadingfiles.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VideoInfo")
public class VideoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Desc")
    private String Desc;
    @Column(name = "category")
    private String category;
    @Column(name = "releaseTime")
    private String releaseTime;
    @Column(name = "releaseDate")
    private String releaseDate;

    public VideoInfo(String name, String desc, String category, String releaseTime, String releaseDate) {
        this.Name = name;
        this.Desc = desc;
        this.category = category;
        this.releaseTime = releaseTime;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }



}
