package com.zghd.entity.platform;

import java.util.Date;

/**
 * 上游统计
 */
public class ReportUpstream {

    //1请求 2返回 3曝光 4点击
    private int type;
    //主键id(时间+appId+slotId+upstreamId的md5值)
    private String id;
    //对应下游请求表主键id
    private String downstreamReportId;
    //appId
    private String appId;
    //slotId
    private String slotId;
    //上游id
    private String upstreamId;
    //上游类型
    private int upstreamType;
    //时间
    private Date createTime;
    //小时
    private int hour;
    //请求
    private int request;
    //返回
    private int response;
    //曝光
    private int look;
    //点击
    private int click;
    //吊起
    private int deeplink;
    //300毫秒
    private int t300;
    //400毫秒
    private int t400;
    //500毫秒
    private int t500;
    //500毫秒+
    private int t1000;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDownstreamReportId() {
        return downstreamReportId;
    }

    public void setDownstreamReportId(String downstreamReportId) {
        this.downstreamReportId = downstreamReportId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getUpstreamId() {
        return upstreamId;
    }

    public void setUpstreamId(String upstreamId) {
        this.upstreamId = upstreamId;
    }

    public int getUpstreamType() {
        return upstreamType;
    }

    public void setUpstreamType(int upstreamType) {
        this.upstreamType = upstreamType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public int getLook() {
        return look;
    }

    public void setLook(int look) {
        this.look = look;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(int deeplink) {
        this.deeplink = deeplink;
    }

    public int getT300() {
        return t300;
    }

    public void setT300(int t300) {
        this.t300 = t300;
    }

    public int getT400() {
        return t400;
    }

    public void setT400(int t400) {
        this.t400 = t400;
    }

    public int getT500() {
        return t500;
    }

    public void setT500(int t500) {
        this.t500 = t500;
    }

    public int getT1000() {
        return t1000;
    }

    public void setT1000(int t1000) {
        this.t1000 = t1000;
    }
}
