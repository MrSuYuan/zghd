package com.zghd.entity.JuMai.response;

import java.util.List;

/**
 * Playpercentage 对象
 */
public class Playpercentage {

    //时间（秒）
    private int checkpoint;
    //跟踪上报地址
    private List<String> urls;

    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
