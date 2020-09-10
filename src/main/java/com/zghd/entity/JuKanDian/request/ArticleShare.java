package com.zghd.entity.JuKanDian.request;

/**
 * 文章分享参数
 */
public class ArticleShare {

    private String appId;
    private String slotId;
    //渠道唯一标识
    private String appkey;
    //APP对应包名
    private String appid;
    //用户初始化时生成的令牌
    private String token;
    //文章id 文章列表返回的artId
    private String shareArtId;
    //文章列表返回的埋点数据ctxData
    private String shareExtra;
    //文章列表返回数据shareTarget
    private String shareTarget;
    //链接：0，图片:1，文字:2 视频:3 文章列表返回的 itemType
    private String shareType;
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

    public String getShareArtId() {
        return shareArtId;
    }

    public void setShareArtId(String shareArtId) {
        this.shareArtId = shareArtId;
    }

    public String getShareExtra() {
        return shareExtra;
    }

    public void setShareExtra(String shareExtra) {
        this.shareExtra = shareExtra;
    }

    public String getShareTarget() {
        return shareTarget;
    }

    public void setShareTarget(String shareTarget) {
        this.shareTarget = shareTarget;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getCtxData() {
        return ctxData;
    }

    public void setCtxData(String ctxData) {
        this.ctxData = ctxData;
    }
}
