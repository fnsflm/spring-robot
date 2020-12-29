package com.example.demo.setu;

import java.util.List;

public class SetuRequest {
    private int code;
    private String msg;
    private int quota;
    private int quota_min_ttl;
    private int count;
    private List<Setu> data;

    public SetuRequest(int code, String msg, int quota, int quota_min_ttl, int count, List<Setu> data) {
        this.code = code;
        this.msg = msg;
        this.quota = quota;
        this.quota_min_ttl = quota_min_ttl;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getQuota_min_ttl() {
        return quota_min_ttl;
    }

    public void setQuota_min_ttl(int quota_min_ttl) {
        this.quota_min_ttl = quota_min_ttl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Setu> getData() {
        return data;
    }

    public void setData(List<Setu> data) {
        this.data = data;
    }
}
