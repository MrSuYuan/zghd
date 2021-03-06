package com.zghd.entity.JuKanDian.request;

/**
 * 文章列表
 */
public class ArticleList {

    private String appId;
    private String slotId;
    //渠道唯一标识
    private String appkey;
    //APP对应包名
    private String appid;
    //用户初始化时生成的令牌
    private String token;
    //文章列表分页参数，从0开始
    private int pageno;
    private String typeid;

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }
}
