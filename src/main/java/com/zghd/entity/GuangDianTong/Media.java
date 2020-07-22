package com.zghd.entity.GuangDianTong;

/**
 * 媒体相关信息
 */
public class Media {

    /**
     * 应用ID
     */
    private String app_id;
    /**
     * 应用包名
     */
    private String app_bundle_id;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_bundle_id() {
        return app_bundle_id;
    }

    public void setApp_bundle_id(String app_bundle_id) {
        this.app_bundle_id = app_bundle_id;
    }
}
