package com.zghd.entity.YuLiang;

/**
 * 余梁app信息
 */
public class App {

    /**
     * APPID
     * @request true
     */
    private String app_id;
    /**
     * APP名称
     * @request true
     */
    private String app_name;
    /**
     * APP信息
     * @request true
     */
    private String app_version;
    /**
     * APP包名
     * @request true
     */
    private String app_package;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getApp_package() {
        return app_package;
    }

    public void setApp_package(String app_package) {
        this.app_package = app_package;
    }
}
