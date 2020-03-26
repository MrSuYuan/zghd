package com.zghd.entity.XiaoZhi;

/**
 * App信息
 */
public class App {

    /**
     * appId,应用id
     * @requert true
     */
    private String app_id;
    /**
     * 推广渠道id
     * @requert true
     */
    private String channel_id;
    /**
     * app名称
     * @requert true
     */
    private String app_name;
    /**
     * app包名
     * @requert true
     */
    private String package_name;
    /**
     * app版本号
     * @requert true
     */
    private String app_version;
    /**
     * 是否具备上报能力 0 / 1
     * @requert true
     */
    private int report_pv_method;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public int getReport_pv_method() {
        return report_pv_method;
    }

    public void setReport_pv_method(int report_pv_method) {
        this.report_pv_method = report_pv_method;
    }
}
