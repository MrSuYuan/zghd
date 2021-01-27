package com.zghd.entity.YouKu.request;

import java.util.List;

/**
 * Video
 */
public class Video {

    //支持播放的视频格式，目前支持： video/x-flv，application/x-shockwave-flash。
    //注意：“优酷”的贴片视频广告，实际上要求DSP先上传创意素材到“优酷”视频系统，并获取返回的播放页URL作为素材的地址，
    // 类似于：http://v.youku.com/v_show/id_XODQyMjY2MzY0.html，在RTB响应中，直接返回该地址即可。由于历史原因，“优酷”RTB请求中mimes均使用video/x-flv，DSP不需要判断这个情况，直接返回播放页地址即可。
    private List<String> mimes;
    //广告展现样式，0: "未知"; 1："instream/linear"即线性贴片素材，包括前贴中贴后贴; 2:"overlay/nonlinear"即视频播放中的悬浮广告;
    // 3："pause"即视频播放暂停中的悬浮广告; 4:"fullscreen"即视频全屏播放时的悬浮广告; 5:"openapp"即开机图广告
    private int linearity;
    //视频广告最短播放时长，单位是秒
    private int minduration;
    //视频广告最长播放时长，单位是秒
    private int maxduration;
    //广告位宽度
    private int w;
    //广告位高度
    private int h;
    //该字段仅在linearity=1时有效；线性贴片，0：前帖；-1：中贴；-2：后贴。
    private int startdelay;

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

    public int getStartdelay() {
        return startdelay;
    }

    public void setStartdelay(int startdelay) {
        this.startdelay = startdelay;
    }
}
