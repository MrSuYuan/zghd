package com.zghd.entity.YouKu.request;

import java.util.List;

/**
 * 视频时长,类型
 */
public class VideoUrl {

    //视频广告最短播放时长，单位是秒
    private int minduration;
    //视频广告最长播放时长，单位是秒
    private int maxduration;
    //广告素材类型mime类型限制
    private List<String> mimes;

    public int getMinduration() {
        return minduration;
    }

    public void setMinduration(int minduration) {
        this.minduration = minduration;
    }

    public int getMaxduration() {
        return maxduration;
    }

    public void setMaxduration(int maxduration) {
        this.maxduration = maxduration;
    }

    public List<String> getMimes() {
        return mimes;
    }

    public void setMimes(List<String> mimes) {
        this.mimes = mimes;
    }
}
