package com.zghd.entity.YouKu.response;

import java.util.List;

public class Seatbid {

    //针对单次曝光的出价
    private List<Bid> bid;

    public List<Bid> getBid() {
        return bid;
    }

    public void setBid(List<Bid> bid) {
        this.bid = bid;
    }
}
