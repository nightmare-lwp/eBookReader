package com.lwp.ebook.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String fictionId;
    private String title;
    private String author;
    private String fictionType;
    private String descs;
    private String cover;
    private String updateTime;
    private String readChapter;

    public Book(){}
    public Book(String fictionId, String title, String author, String fictionType, String descs, String cover, String updateTime,String readChapter) {
        this.fictionId = fictionId;
        this.title = title;
        this.author = author;
        this.fictionType = fictionType;
        this.descs = descs;
        this.cover = cover;
        this.updateTime = updateTime;
        this.readChapter=readChapter;
    }

    @Override
    public String toString() {
        return "Book{" +
                "fictionId='" + fictionId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", fictionType='" + fictionType + '\'' +
                ", descs='" + descs + '\'' +
                ", cover='" + cover + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
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

    public String getReadChapter() {
        return readChapter;
    }

    public void setReadChapter(String readChapter) {
        this.readChapter = readChapter;
    }
}
