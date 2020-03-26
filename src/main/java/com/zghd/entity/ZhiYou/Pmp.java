package com.zghd.entity.ZhiYou;

import java.util.List;

//广告位竞价
public class Pmp {
    //私有拍卖 1是 2否
    private int private_auction;
    //私有交易,流量采买标准
    private List<Deal> deals;

    public int getPrivate_auction() {
        return private_auction;
    }

    public void setPrivate_auction(int private_auction) {
        this.private_auction = private_auction;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
}
