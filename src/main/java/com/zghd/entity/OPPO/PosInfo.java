package com.zghd.entity.OPPO;

public class PosInfo {

    //联盟广告位ID.
    private String id;
    //联盟广告位类型
    private int posType;
    //广告位宽度，以px 为单位。
    private int w;
    //广告位高度，以px 为单位。
    private int h;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosType() {
        return posType;
    }

    public void setPosType(int posType) {
        this.posType = posType;
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
}
