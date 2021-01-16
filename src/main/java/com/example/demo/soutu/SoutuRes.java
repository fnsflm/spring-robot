package com.example.demo.soutu;

import com.google.gson.Gson;

import java.util.List;

public class SoutuRes {

    public class Header {
        String similarity;
        String thumbnail;
        int index_id;
        String index_name;
        int dupes;

        public String getSimilarity() {
            return similarity;
        }

        public void setSimilarity(String similarity) {
            this.similarity = similarity;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getIndex_id() {
            return index_id;
        }

        public void setIndex_id(int index_id) {
            this.index_id = index_id;
        }

        public String getIndex_name() {
            return index_name;
        }

        public void setIndex_name(String index_name) {
            this.index_name = index_name;
        }

        public int getDupes() {
            return dupes;
        }

        public void setDupes(int dupes) {
            this.dupes = dupes;
        }
    }

    public class Data {
        List<String> ext_urls;
        String title;
        int pixiv_id = -1;
        String member_name;
        int member_id;

        public List<String> getExt_urls() {
            return ext_urls;
        }

        public void setExt_urls(List<String> ext_urls) {
            this.ext_urls = ext_urls;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPixiv_id() {
            return pixiv_id;
        }

        public void setPixiv_id(int pixiv_id) {
            this.pixiv_id = pixiv_id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }
    }

    Header header;
    Data data;

    public SoutuRes(Header header, Data data) {
        this.header = header;
        this.data = data;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }
}
