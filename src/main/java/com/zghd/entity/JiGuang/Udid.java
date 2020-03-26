package com.zghd.entity.JiGuang;

/**
 * 设备唯一标识
 */
public class Udid {

    /**
     * iOS IDFA
     * @request true
     */
    private String idfa;
    /**
     * Android IMEI
     * @request true
     */
    private String imei;
    /**
     * Android ID
     * @request true
     */
    private String android_id;
    /**
     * 设备网卡MAC地址
     * @request true
     */
    private String mac;

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
