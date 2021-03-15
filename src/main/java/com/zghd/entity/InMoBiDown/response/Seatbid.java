package com.zghd.entity.InMoBiDown.response;

import java.util.List;

public class Seatbid {

    private String seat;
    private List<Bid> bid;

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public List<Bid> getBid() {
        return bid;
    }

    public void setBid(List<Bid> bid) {
        this.bid = bid;
    }
}
