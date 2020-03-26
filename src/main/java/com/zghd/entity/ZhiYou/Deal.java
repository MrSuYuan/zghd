package com.zghd.entity.ZhiYou;
//竞价描述
public class Deal {
    //竞价编号随机字符串md5加密小写
    private int id;
    //1第一价格 2第二价格 3bidfloor的值为交易价格
    private int at;
    //交易价格(单位分)
    private Float bidfloor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public Float getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(Float bidfloor) {
        this.bidfloor = bidfloor;
    }
}
