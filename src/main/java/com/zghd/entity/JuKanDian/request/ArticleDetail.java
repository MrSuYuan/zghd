package com.zghd.entity.JuKanDian.request;

/**
 * 文章详情请求参数
 */
public class ArticleDetail {

    private String appId;
    private String slotId;
    //渠道唯一标识
    private String appkey;
    //APP对应包名
    private String appid;
    //用户初始化时生成的令牌
    private String token;
    //文章类型 0 文章 1 视频
    private String artClassify;
    //文章ID
    private String artId;
    //埋点数据
    private String ctxData;

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

    public String getArtClassify() {
        return artClassify;
    }

    public void setArtClassify(String artClassify) {
        this.artClassify = artClassify;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getCtxData() {
        return ctxData;
    }

    public void setCtxData(String ctxData) {
        this.ctxData = ctxData;
    }
}
