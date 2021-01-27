package com.zghd.entity.YouKu.request;

/**
 * Banner
 */
public class Banner {

    //广告位宽度
    private int w;
    //广告位高度
    private int h;
    //广告位未知，0：未知；1：首屏；2：次屏
    private int pos;

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
}
