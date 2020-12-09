package com.zghd.entity.VIVO.response;

import java.util.List;

/**
 * 广告数据数组
 */
public class AdDTO {

    /**
     * 广告id
     */
    private String adId;
    /**
     * 广告位类型
     * 2开屏 3banner 4插屏 5原生 9激励视频
     */
    private int adType;
    /**
     * 广告形式
     * 1普通网址
     */
    private int adStyle;
    /**
     * 创意类型
     * 1大图 2小图 3组图 4纯图片 5视频+前导图 7视频 20纯icon(应用下载类图标)
     */
    private int materialType;
    /**
     * 目标落地页
     */
    private String targetUrl;
    /**
     * depplink
     */
    private String deeplink;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 广告主头像图片链接地址
     */
    private String sourceAvatar;
    /**
     * 图片素材
     */
    private Image image;
    /**
     * 图片素材(组图时使用这个字段)
     */
    private List<Image> imageList;
    /**
     * 视频素材
     */
    private Video video;
    /**
     * deeplink被吊起的包名
     */
    private String appPackage;
    /**
     * deeplink被吊起应用icon地址
     */
    private String appIconUrl;
    /**
     * deeplink被吊起应用名称
     */
    private String appName;
    /**
     * 广告上报检测
     */
    private List<Tracking> tarckingList;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getAdStyle() {
        return adStyle;
    }

    public void setAdStyle(int adStyle) {
        this.adStyle = adStyle;
    }

    public int getMaterialType() {
        return materialType;
    }

    public void setMaterialType(int materialType) {
        this.materialType = materialType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceAvatar() {
        return sourceAvatar;
    }

    public void setSourceAvatar(String sourceAvatar) {
        this.sourceAvatar = sourceAvatar;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppIconUrl() {
        return appIconUrl;
    }

    public void setAppIconUrl(String appIconUrl) {
        this.appIconUrl = appIconUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<Tracking> getTarckingList() {
        return tarckingList;
    }

    public void setTarckingList(List<Tracking> tarckingList) {
        this.tarckingList = tarckingList;
    }
}
