package com.zghd.entity.InMoBiDown.request;

import java.util.List;

public class ReqPmp {

    private boolean privateauction;
    //竞价使用
    private List<ReqDeal> deals;

    public boolean isPrivateauction() {
        return privateauction;
    }

    public void setPrivateauction(boolean privateauction) {
        this.privateauction = privateauction;
    }

    public List<ReqDeal> getDeals() {
        return deals;
    }

    public void setDeals(List<ReqDeal> deals) {
        this.deals = deals;
    }
}
