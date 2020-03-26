package com.zghd.entity.ZhongMeng;

/**
 * 广告位基本信息
 */
public class AdSlotInfo {

    /**
     * ⼴广告位⽀支持的物料料类型 e.g.
     * jpg/gif/png/mp4/webp/flv/swf/txt/icon/c
     * 其中:txt 指⽂文字链,icon指图⽂文,c指富⽂文本
     * 纯图⽚片素材请求可以等价设置mimes为
     * img
     */
    private String mimes;
    /**
     * 广告位长度
     */
    private int slotWidth;
    /**
     * 广告位宽度
     */
    private int slotHeight;
    /**
     * 视频广告位允许最短时长(s)
     */
    private int minDuration;
    /**
     * 视频广告位允许最大时长(s)
     */
    private int maxDuration;
    /**
     * 广告展示位置,顶部:1;底部:2;信息流内:3; 中部:4;全屏:5
     */
    private int pos;
    /**
     * 当前视频的标题(视频类广告位)
     */
    private String videoTitle;
    /**
     * 当前视频内容⻓度(单位秒,视频类广告位)
     */
    private int videoLength;

    public String getMimes() {
        return mimes;
    }

    public void setMimes(String mimes) {
        this.mimes = mimes;
    }

    public int getSlotWidth() {
        return slotWidth;
    }

    public void setSlotWidth(int slotWidth) {
        this.slotWidth = slotWidth;
    }

    public int getSlotHeight() {
        return slotHeight;
    }

    public void setSlotHeight(int slotHeight) {
        this.slotHeight = slotHeight;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public int getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(int videoLength) {
        this.videoLength = videoLength;
    }
}
