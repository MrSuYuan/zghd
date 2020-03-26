package com.zghd.entity.ZGHDNewsResponse;

/**
 * 新闻物料信息
 */
public class Material {

    //物料类型 1新闻 2广告
    private int materialType;
    //新闻内容
    private News news;
    //广告内容
    private Ad ad;

    public int getMaterialType() {
        return materialType;
    }

    public void setMaterialType(int materialType) {
        this.materialType = materialType;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
