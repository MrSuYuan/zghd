package com.zghd.entity.ZGHDResponse;

import java.util.List;

/**
 * 视频播放进度上报
 */
public class Track {

    //视频播放进度
    //0-0  1-0.25  2-0.5  3-0.75  4-1
    private int type;
    private List<String> urls;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
