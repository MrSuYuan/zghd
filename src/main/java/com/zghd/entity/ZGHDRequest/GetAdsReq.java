package com.zghd.entity.ZGHDRequest;

/**
 * 下游请求参数
 */
public class GetAdsReq {

    /**
     * 请求id
     */
    private String requestId;
    /**
     * 应用信息
     */
    private App app;
    /**
     * 广告位信息
     */
    private Slot slot;
    /**
     * 设备信息
     */
    private Device device;
    /**
     * 网络信息
     */
    private Network network;
    /**
     * 瑞狮专用新闻分类
     */
    private News news;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
