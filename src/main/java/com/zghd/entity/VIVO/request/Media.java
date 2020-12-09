package com.zghd.entity.VIVO.request;

/**
 * 媒体信息
 */
public class Media {

    /**
     * meiti id
     */
    private String mediaId;
    /**
     * 应用包名
     */
    private String appPackage;
    /**
     * 应用版本号(整形)
     */
    private int appVersion;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }
}
