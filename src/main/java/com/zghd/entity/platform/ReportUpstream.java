package com.zghd.entity.platform;

import java.util.Date;

/**
 * 上游统计
 */
public class ReportUpstream {

    //主键id(时间+appId+slotId+upstreamId的md5值)
    private String id;
    //对应下游请求表主键id
    private String downstreamReportId;
    //上游id
    private String upstreamId;
    //appId
    private String appId;
    //slotId
    private String slotId;
    //上游类型
    private int upstreamType;
    //时间
    private Date createTime;
    private int request;
    private int response;
    private int look;
    private int click;
    //1请求 2返回 3曝光 4点击
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getDownstreamReportId() {
        return downstreamReportId;
    }

    public void setDownstreamReportId(String downstreamReportId) {
        this.downstreamReportId = downstreamReportId;
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
}
