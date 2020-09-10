package com.zghd.entity.JuKanDian.request;

import java.util.Date;

/**
 * 文章统计
 */
public class ArticleReport {

    private int id;
    private int type;
    private String appId;
    private String slotId;
    private String upstreamAppkey;
    private String upstreamAppid;
    private Date createTime;
    private String create_Time;
    private int hour;
    private int userTimes;
    private int listTimes;
    private int detailTimes;
    private int shareTimes;
    private int backTimes;

    public String getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(String create_Time) {
        this.create_Time = create_Time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUpstreamAppkey() {
        return upstreamAppkey;
    }

    public void setUpstreamAppkey(String upstreamAppkey) {
        this.upstreamAppkey = upstreamAppkey;
    }

    public String getUpstreamAppid() {
        return upstreamAppid;
    }

    public void setUpstreamAppid(String upstreamAppid) {
        this.upstreamAppid = upstreamAppid;
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

    public int getUserTimes() {
        return userTimes;
    }

    public void setUserTimes(int userTimes) {
        this.userTimes = userTimes;
    }

    public int getListTimes() {
        return listTimes;
    }

    public void setListTimes(int listTimes) {
        this.listTimes = listTimes;
    }

    public int getDetailTimes() {
        return detailTimes;
    }

    public void setDetailTimes(int detailTimes) {
        this.detailTimes = detailTimes;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public int getBackTimes() {
        return backTimes;
    }

    public void setBackTimes(int backTimes) {
        this.backTimes = backTimes;
    }
}
