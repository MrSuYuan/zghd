package com.zghd.entity.JuKanDian.response;

public class ArticleOtherBackResp {
    private String appId;
    private String slotId;
    //APP对应的用户标识
    private String userid;
    //文章ID
    private String aid;
    //标题
    private String title;
    //分享key
    private String sharekey;
    //流水ID
    private String snid;
    //单次有效阅读奖励（毫）
    private int reward;
    //计费时间
    private String createtime;
    //打开文章的ip地址
    private String ip;
    //微信openid
    private String uuid;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSharekey() {
        return sharekey;
    }

    public void setSharekey(String sharekey) {
        this.sharekey = sharekey;
    }

    public String getSnid() {
        return snid;
    }

    public void setSnid(String snid) {
        this.snid = snid;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
