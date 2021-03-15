package com.zghd.entity.JingDong.request;

import java.util.List;

public class Video {

    private int w;
    private int h;
    //最小时长
    private int minduration;
    //最大时长
    private int maxduration;
    //广告开始播放的延时时间
    private int startdelay;
    //支持的类型 video/mp4
    private List<String> mimes;
    //1视频信息流中展现
    private int linearity;
    //最小比特率
    private int minbitrate;
    //最大比特率
    private int maxbitrate;

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

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

    public int getStartdelay() {
        return startdelay;
    }

    public void setStartdelay(int startdelay) {
        this.startdelay = startdelay;
    }

    public List<String> getMimes() {
        return mimes;
    }

    public void setMimes(List<String> mimes) {
        this.mimes = mimes;
    }

    public int getLinearity() {
        return linearity;
    }

    public void setLinearity(int linearity) {
        this.linearity = linearity;
    }

    public int getMinbitrate() {
        return minbitrate;
    }

    public void setMinbitrate(int minbitrate) {
        this.minbitrate = minbitrate;
    }

    public int getMaxbitrate() {
        return maxbitrate;
    }

    public void setMaxbitrate(int maxbitrate) {
        this.maxbitrate = maxbitrate;
    }
}
