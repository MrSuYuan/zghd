package com.zghd.entity.ZhiYou;

import java.util.List;

//广告位视频信息
public class Video {
    //宽
    private int w;
    //高
    private int h;
    //类型 1原生视频 2激励视频
    private int type;
    //视频播放最小时长
    private int minduration;
    //视频播放最大时长
    private int maxduration;
    //视频在广告位中的位置(0前贴 1中贴 2后贴)
    private int startdelay;
    //展示方式(1视频流中  2视频内容上悬浮展示)
    private int linearity;
    //默认0
    private int pos;
    //默认 video/mp4
    private List<String> mimes;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getLinearity() {
        return linearity;
    }

    public void setLinearity(int linearity) {
        this.linearity = linearity;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public List<String> getMimes() {
        return mimes;
    }

    public void setMimes(List<String> mimes) {
        this.mimes = mimes;
    }
}
