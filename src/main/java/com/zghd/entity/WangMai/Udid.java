package com.zghd.entity.WangMai;

/**
 * 手机信息
 */
public class Udid {

    /**
     * iOS 设备唯一标识码，iOS 必填
     */
    private String idfa;
    /**
     * md5加密idfa
     */
    private String idfa_md5;
    /**
     * 供应商标识符
     */
    private String idfv;
    /**
     * Android设备唯一标识码，安卓必填
     */
    private String imei;
    /**
     * md5加密imei
     */
    private String imei_md5;
    /**
     * Android设备ID，安卓必填
     */
    private String android_id;
    /**
     * md5加密Android设备ID
     */
    private String android_id_md5;
    /**
     * Mac地址
     */
    private String mac;
    /**
     * Android设备的oaid
     */
    private String oaid;

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfa_md5() {
        return idfa_md5;
    }

    public void setIdfa_md5(String idfa_md5) {
        this.idfa_md5 = idfa_md5;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImei_md5() {
        return imei_md5;
    }

    public void setImei_md5(String imei_md5) {
        this.imei_md5 = imei_md5;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getAndroid_id_md5() {
        return android_id_md5;
    }

    public void setAndroid_id_md5(String android_id_md5) {
        this.android_id_md5 = android_id_md5;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }
}
