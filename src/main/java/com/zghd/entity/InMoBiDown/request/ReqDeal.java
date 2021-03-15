package com.zghd.entity.InMoBiDown.request;

public class ReqDeal {

    //Deal ID，由InMobi 生成或双方约定
    private int id;
    //底价 元
    private double bidfloor;
    private String bidfloorcur;
    //1一价结算  2二价结算
    private int at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(double bidfloor) {
        this.bidfloor = bidfloor;
    }

    public String getBidfloorcur() {
        return bidfloorcur;
    }

    public void setBidfloorcur(String bidfloorcur) {
        this.bidfloorcur = bidfloorcur;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }
}
