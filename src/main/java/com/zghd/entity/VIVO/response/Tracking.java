package com.zghd.entity.VIVO.response;

import java.util.List;

/**
 * 上报信息
 */
public class Tracking {

    /**
     * 上报类型
     * 2曝光 3点击 5视频开始播放 625% 750% 875% 9播放完成 21deeplink吊起
     */
    private int trackingEvent;
    /**
     * 上报地址
     */
    private List<String> trackUrls;

    public int getTrackingEvent() {
        return trackingEvent;
    }

    public void setTrackingEvent(int trackingEvent) {
        this.trackingEvent = trackingEvent;
    }

    public List<String> getTrackUrls() {
        return trackUrls;
    }

    public void setTrackUrls(List<String> trackUrls) {
        this.trackUrls = trackUrls;
    }
}
