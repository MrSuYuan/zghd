package com.zghd.entity.YouKu.request;

import java.util.List;

/**
 * 图片尺寸
 */
public class ImageUrl {

    //广告素材宽度限制
    private int w;
    //广告素材高度限制
    private int h;
    //广告素材类型mime类型限制
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

    public List<String> getMimes() {
        return mimes;
    }

    public void setMimes(List<String> mimes) {
        this.mimes = mimes;
    }
}
