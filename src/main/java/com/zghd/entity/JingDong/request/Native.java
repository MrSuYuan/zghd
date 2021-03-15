package com.zghd.entity.JingDong.request;

import java.util.List;

public class Native {

    private int w;
    private int h;
    //素材个数
    private int count;
    //1开屏/横幅/单图信息流  2视频信息流  3三图信息流
    private int imgnum;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImgnum() {
        return imgnum;
    }

    public void setImgnum(int imgnum) {
        this.imgnum = imgnum;
    }
}
