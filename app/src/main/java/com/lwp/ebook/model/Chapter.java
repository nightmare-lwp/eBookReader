package com.lwp.ebook.model;

public class Chapter {

    private String title;
    private String chapterId;

    public Chapter(String title, String chapterId) {
        this.title = title;
        this.chapterId = chapterId;
    }
    public Chapter(){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapterId() {
        return chapterId;
    }



    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}
