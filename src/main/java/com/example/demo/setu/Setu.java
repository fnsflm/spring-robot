package com.example.demo.setu;

import com.google.gson.Gson;

import java.util.List;

public class Setu {
    int pid;
    int p;
    int uid;
    String title;
    String author;
    String url;
    boolean r18;
    int width;
    int height;
    List<String> tags;

    public Setu(int pid, int p, int uid, String title, String author, String url, boolean r18, int width, int height, List<String> tags) {
        this.pid = pid;
        this.p = p;
        this.uid = uid;
        this.title = title;
        this.author = author;
        this.url = url;
        this.r18 = r18;
        this.width = width;
        this.height = height;
        this.tags = tags;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isR18() {
        return r18;
    }

    public void setR18(boolean r18) {
        this.r18 = r18;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public String getJson(){
        return (new Gson()).toJson(this);
    }
}
