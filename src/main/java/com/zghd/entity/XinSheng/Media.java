package com.zghd.entity.XinSheng;

/**
 * 媒体信息
 */
public class Media {

    /**
     * 媒体的广告位id
     */
    private String slotid;
    /**
     * 媒体方用户id
     * 格式为媒体ID:媒体的用户ID 如：100001:e0e38550
     * 需要前置完成cookie mapping(cm)过程，媒体ID为在cm时分配的ID。
     */
    private String uid;
    /**
     * CPM底价，单位分
     */
    private String bf;
    /**
     * 移动流量的App版本号(移动流量必填)
     */
    private String app_version;
    /**
     * 移动流量的包名(移动流量必填)
     */
    private String package_name;

    public String getSlotid() {
        return slotid;
    }

    public void setSlotid(String slotid) {
        this.slotid = slotid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
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
