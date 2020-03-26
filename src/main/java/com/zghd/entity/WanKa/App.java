package com.zghd.entity.WanKa;

/**
 * 应用信息
 */
public class App {

    /**
     * 应用id，玩咖优盟媒体服务平台提供的app_id
     * @required true
     */
    private String app_id;
    /**
     * 应用版本，格式a.b.c； a,b,c取值范围：[0,Integer.MAX_VALUE]
     * @required true
     */
    private String app_version;
    /**
     * 应用包名，必须与平台填写的一致
     * @required true
     */
    private String package_name;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }
}
