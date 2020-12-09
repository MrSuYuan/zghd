package com.zghd.entity.JuKanDian.response;
/**
 *上游给我们
 */
public class ArticleOtherBack {
    //渠道唯一标识
    private String appkey;
    //APP对应包名
    private String appid;
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

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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
