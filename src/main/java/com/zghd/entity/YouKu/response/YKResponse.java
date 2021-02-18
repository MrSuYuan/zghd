package com.zghd.entity.YouKu.response;

import java.util.List;

/**
 * 优酷返回素材
 */
public class YKResponse {

    //请求ID
    private String id;
    //DSP给出的该次竞价的ID
    private String bidid;
    //DSP出价
    private List<Seatbid> seatbid;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public List<Seatbid> getSeatbid() {
        return seatbid;
    }

    public void setSeatbid(List<Seatbid> seatbid) {
        this.seatbid = seatbid;
    }
}
