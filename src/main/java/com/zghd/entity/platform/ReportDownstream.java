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
