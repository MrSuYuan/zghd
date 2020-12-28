package com.zghd.entity.platform;

import java.util.Date;

/**
 * 下游统计
 */
public class ReportDownstream {

    //id(广告位和时间复合生成)
    private String downstreamReportId;
    //appId
    private String appId;
    //平台slotId
    private String slotId;
    //时间
    private Date createTime;
    //数量
    private int downstreamRequest;
    //小时
    private int hour;
    //200毫秒
    private int t200;
    //250毫秒
    private int t250;
    //300毫秒
    private int t300;
    //400毫秒
    private int t400;
    //500毫秒
    private int t500;
    //500毫秒+
    private int t1000;

    public int getT200() {
        return t200;
    }

    public void setT200(int t200) {
        this.t200 = t200;
    }

    public int getT250() {
        return t250;
    }

    public void setT250(int t250) {
        this.t250 = t250;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDownstreamReportId() {
        return downstreamReportId;
    }

    public void setDownstreamReportId(String downstreamReportId) {
        this.downstreamReportId = downstreamReportId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getDownstreamRequest() {
        return downstreamRequest;
    }

    public void setDownstreamRequest(int downstreamRequest) {
        this.downstreamRequest = downstreamRequest;
    }
}
