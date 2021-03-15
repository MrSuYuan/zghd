package com.zghd.entity.JingDong.response;

public class JDResponse {

    //请求id
    private String id;
    //京东生成的bid id
    private String bidid;
    //响应席位
    private Seatbid seatbid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBidid() {
        return bidid;
    }

    public void setBidid(String bidid) {
        this.bidid = bidid;
    }

    public Seatbid getSeatbid() {
        return seatbid;
    }

    public void setSeatbid(Seatbid seatbid) {
        this.seatbid = seatbid;
    }

}
