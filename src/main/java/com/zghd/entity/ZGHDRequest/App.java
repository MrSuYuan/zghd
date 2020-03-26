package com.zghd.entity.ZGHDRequest;

/**
 * app信息
 */
public class App {

    /**
     * 应用ID
     */
    private String appId;
    /**
     * 应用包名
     */
    private String appPackage;
    /**
     * 应用版本
     */
    private String appVersion;
    /**
     * 应用名称
     */
    private String appName;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
