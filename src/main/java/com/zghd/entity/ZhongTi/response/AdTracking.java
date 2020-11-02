package com.zghd.entity.ZhongTi.response;

import java.util.List;

public class AdTracking {

    private String tracking_event;
    private int point;
    private List<String> tracking_url;

    public String getTracking_event() {
        return tracking_event;
    }

    public void setTracking_event(String tracking_event) {
        this.tracking_event = tracking_event;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<String> getTracking_url() {
        return tracking_url;
    }

    public void setTracking_url(List<String> tracking_url) {
        this.tracking_url = tracking_url;
    }
}
