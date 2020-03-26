package com.zghd.entity.ZhiYou;

import java.util.List;

//图片广告必填
public class Banner {
    //宽
    private int w;
    //gao
    private int h;
    //广告位在屏幕的编号,默认0
    private int pos;
    //图片类型编号,默认 3
    private int type;
    //默认填写 image/gif,image/jpeg,image/jpg,image/png
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getMimes() {
        return mimes;
    }

    public void setMimes(List<String> mimes) {
        this.mimes = mimes;
    }
}
