package com.zghd.entity.JuKanDian.response;

import java.util.List;

/**
 * 文章列表详情
 */
public class ArticleListDetail {

    //文章类型 0 文章 1 视频
    private int artClassify;
    //文章ID
    private String artId;
    //文章标题
    private String artTitle;
    //文章分类ID
    private int artTypeId;
    //图片显示模式 0小图 1大图 2三图
    private int imageMode;
    //图片url地址数组
    private List<String> imageUrl;
    //类型 0文章 1广告
    private String itemType;
    //阅读数量
    private String readcount;
    //列表场景 精品:list 热文:hot 收藏:store
    private String sceneType;
    //分享图标图片,微信小图标
    private String shareImage;
    //分享被有效阅读单次收益说明
    private String shareProfit;
    //分享目标 timegorup:好友群 timeline:朋友圈
    private String shareTarget;
    //分享提示说明
    private String shareText;
    //埋点数据，打开文章详情的时候需要透传过去
    private String ctxData;

    public int getArtClassify() {
        return artClassify;
    }

    public void setArtClassify(int artClassify) {
        this.artClassify = artClassify;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public int getArtTypeId() {
        return artTypeId;
    }

    public void setArtTypeId(int artTypeId) {
        this.artTypeId = artTypeId;
    }

    public int getImageMode() {
        return imageMode;
    }

    public void setImageMode(int imageMode) {
        this.imageMode = imageMode;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getReadcount() {
        return readcount;
    }

    public void setReadcount(String readcount) {
        this.readcount = readcount;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public String getShareProfit() {
        return shareProfit;
    }

    public void setShareProfit(String shareProfit) {
        this.shareProfit = shareProfit;
    }

    public String getShareTarget() {
        return shareTarget;
    }

    public void setShareTarget(String shareTarget) {
        this.shareTarget = shareTarget;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getCtxData() {
        return ctxData;
    }

    public void setCtxData(String ctxData) {
        this.ctxData = ctxData;
    }
}
