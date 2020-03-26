package com.zghd.entity.ZGHDNewsResponse;

import java.util.List;

/**
 * 广告内容
 */
public class Ad {

    //广告id
    private String id;
    //标题
    private String title;
    //描述
    private String desc;
    //大图
    private List<Image> images;
    //小图
    private List<Image> icons;
    //落地页
    private String landPage;
    //deeplink
    private String deeplink;
    //1网址浏览类 2下载类
    private int interactionType;
    //曝光上报
    private List<String> showUrl;
    //点击上报
    private List<String> clickUrl;
    //下载上报
    private List<String> downLoadUrl;
    //下载完成上报
    private List<String> downLoadEndUrl;
    //安装上报
    private List<String> installUrl;
    //安装完成上报
    private List<String> installEndUrl;
    //打开上报
    private List<String> openUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Image> getIcons() {
        return icons;
    }

    public void setIcons(List<Image> icons) {
        this.icons = icons;
    }

    public String getLandPage() {
        return landPage;
    }

    public void setLandPage(String landPage) {
        this.landPage = landPage;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public int getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(int interactionType) {
        this.interactionType = interactionType;
    }

    public List<String> getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(List<String> showUrl) {
        this.showUrl = showUrl;
    }

    public List<String> getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(List<String> clickUrl) {
        this.clickUrl = clickUrl;
    }

    public List<String> getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(List<String> downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }

    public List<String> getDownLoadEndUrl() {
        return downLoadEndUrl;
    }

    public void setDownLoadEndUrl(List<String> downLoadEndUrl) {
        this.downLoadEndUrl = downLoadEndUrl;
    }

    public List<String> getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(List<String> installUrl) {
        this.installUrl = installUrl;
    }

    public List<String> getInstallEndUrl() {
        return installEndUrl;
    }

    public void setInstallEndUrl(List<String> installEndUrl) {
        this.installEndUrl = installEndUrl;
    }

    public List<String> getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(List<String> openUrl) {
        this.openUrl = openUrl;
    }
}
