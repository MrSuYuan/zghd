package com.zghd.entity.YouKu.request;

/**
 * 竞价信息
 */
public class Deal {

    //符合条件的deal id
    private String id;
    //竞价的方式，目前都是1，即第一竞价法。最高的deal获得竞价成功，取最高出价作为最终胜出价。注：只有当多个Deal同时响应时才互相之间竞价。这个字段和OpenRTB相同。
    private int at;
    //deal 价格,单位是分/千次曝光,即CPM。 1.3.0.3 版本新增
    private float bidfloor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public float getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(float bidfloor) {
        this.bidfloor = bidfloor;
    }
}
