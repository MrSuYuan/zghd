package com.zghd.entity.YongQi;

import java.util.List;

/**
 * 激励视频
 */
public class Video {

    /**
     * 广告位宽度
     */
    private int w;
    /**
     * 广告位高度
     */
    private int h;
    /**
     * 支持播放的广告格式,当前支持 video/mp4
     */
    private List<String> mimes;
    /**
     * 视频广告最短播放时长  单位:秒
     */
    private int minduration;
    /**
     * 视频广告最长播放时长  单位:秒
     */
    private int maxduration;
    /**
     * 广告展现样式,1(目前只支持1)
     */
    private int linearity;
    /**
     * 延迟
     */
    private int startdelay;
    /**
     *
     */
    private int pos;
    /**
     * 7激励视频(目前只做激励视频)
     */
    private int video_type;

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

    public List<String> getMimes() {
        return mimes;
    }

    public void setMimes(List<String> mimes) {
        this.mimes = mimes;
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

    public int getLinearity() {
        return linearity;
    }

    public void setLinearity(int linearity) {
        this.linearity = linearity;
    }

    public int getStartdelay() {
        return startdelay;
    }

    public void setStartdelay(int startdelay) {
        this.startdelay = startdelay;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getVideo_type() {
        return video_type;
    }

    public void setVideo_type(int video_type) {
        this.video_type = video_type;
    }
}
