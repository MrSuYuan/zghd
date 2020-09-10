package com.zghd.entity.JuKanDian.request;

/**
 * 查询上游信息
 */
public class Upstream {

    private String appId;
    private String slotId;
    private String upstreamAppkey;
    private String upstreamAppid;
    private String backUrl;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getUpstreamAppkey() {
        return upstreamAppkey;
    }

    public void setUpstreamAppkey(String upstreamAppkey) {
        this.upstreamAppkey = upstreamAppkey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUpstreamAppid() {
        return upstreamAppid;
    }

    public void setUpstreamAppid(String upstreamAppid) {
        this.upstreamAppid = upstreamAppid;
    }
}
