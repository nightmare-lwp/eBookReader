package com.lwp.ebook.model.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    private String fictionId;
    private String title;
    private String author;
    private String fictionType;
    private String descs;
    private String cover;
    private String updateTime;
    public int position;

    public User() {
    }
    @Ignore
    public User(int userId, String fictionId, String title, String author, String fictionType, String descs, String cover, String updateTime, int position) {
        this.userId = userId;
        this.fictionId = fictionId;
        this.title = title;
        this.author = author;
        this.fictionType = fictionType;
        this.descs = descs;
        this.cover = cover;
        this.updateTime = updateTime;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFictionId() {
        return fictionId;
    }

    public void setFictionId(String fictionId) {
        this.fictionId = fictionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFictionType() {
        return fictionType;
    }

    public void setFictionType(String fictionType) {
        this.fictionType = fictionType;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
