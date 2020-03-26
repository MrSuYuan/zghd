package com.zghd.entity.WangMai;

/**
 * app信息
 */
public class App {

    /**
     * 版本信息
     */
    private AppVersion app_version;
    /**
     * 包名
     */
    private String pkgName;
    /**
     * app名称
     */
    private String appName;
    /**
     * app下载路径
     */
    private String storeurl;

    public AppVersion getApp_version() {
        return app_version;
    }

    public void setApp_version(AppVersion app_version) {
        this.app_version = app_version;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getStoreurl() {
        return storeurl;
    }

    public void setStoreurl(String storeurl) {
        this.storeurl = storeurl;
    }
}
