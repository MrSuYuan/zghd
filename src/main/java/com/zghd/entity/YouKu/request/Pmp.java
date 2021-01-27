package com.zghd.entity.YouKu.request;

import java.util.List;

/**
 * 竞价对象
 */
public class Pmp {

    //竞价对象元素
    private List<Deal> deals;

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
}
