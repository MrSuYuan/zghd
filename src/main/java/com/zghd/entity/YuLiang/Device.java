package com.zghd.entity.YuLiang;

/**
 * 设备信息
 */
public class Device {

    /**
     * 设备操作系统如"iOS" “android”
     * @request true
     */
    private String os;
    /**
     * 设备品牌
     * @request true
     */
    private String vendor;
    /**
     * 设备型号
     * @request true
     */
    private String model;
    /**
     * 设备操作系统版本
     * @request true
     */
    private String osv;
    /**
     * 设备IDFA
     * @request true
     */
    private String idfa;
    /**
     * 设备IMEI
     * @request true
     */
    private String imei;
    /**
     * MD5加密后的IDFA
     * @request true
     */
    private String idfa_md5;
    /**
     * MD5加密后的IMEI
     * @request true
     */
    private String imei_md5;
    /**
     * 安卓ID
     * @request true
     */
    private String android_id;
    /**
     * MAC
     * @request true
     */
    private String mac;
    /**
     * 第三方用户ID
     * @request false
     */
    private String third_user_id;
    /**
     * 设备屏幕朝向未知：-1,竖屏： 1,反向竖屏： 2, mm： 3,反向横屏： 4
     * @request true
     */
    private int ori;
    /**
     * 屏幕宽度
     * @request false
     */
    private int screen_width;
    /**
     * 屏幕高度
     * @request false
     */
    private int screen_height;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOsv() {
        return osv;
    }

    public void setOsv(String osv) {
        this.osv = osv;
    }

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

    public String getIdfa_md5() {
        return idfa_md5;
    }

    public void setIdfa_md5(String idfa_md5) {
        this.idfa_md5 = idfa_md5;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getThird_user_id() {
        return third_user_id;
    }

    public void setThird_user_id(String third_user_id) {
        this.third_user_id = third_user_id;
    }

    public int getOri() {
        return ori;
    }

    public void setOri(int ori) {
        this.ori = ori;
    }

    public int getScreen_width() {
        return screen_width;
    }

    public void setScreen_width(int screen_width) {
        this.screen_width = screen_width;
    }

    public int getScreen_height() {
        return screen_height;
    }

    public void setScreen_height(int screen_height) {
        this.screen_height = screen_height;
    }
}
