package com.zghd.entity.DianKai.response;

import java.util.List;

public class VideoPlay {

    private int second;
    private List<String> urls;

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
