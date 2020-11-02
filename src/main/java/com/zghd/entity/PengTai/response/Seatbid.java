package com.zghd.entity.PengTai.response;

import java.util.List;

public class Seatbid {

    private List<Bid> bid;
    private String seat;

    public List<Bid> getBid() {
        return bid;
    }

    public void setBid(List<Bid> bid) {
        this.bid = bid;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
