package com.zghd.entity.GuangDianTong;

/**
 * 设备信息
 */
public class Device {

    /**
     * 操作系统 ios android
     */
    private String os;
    /**
     * os版本 unknown(未知需要填写)
     */
    private String os_version;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 设备厂商
     */
    private String manufacturer;
    /**
     * 设备类型 0未知 1手机 2平板
     */
    private int device_type;
    /**
     * 设备宽
     */
    private int screen_width;
    /**
     * 设备高
     */
    private int screen_height;
    /**
     * 横竖屏 0竖屏 90横屏
     */
    private int orientation;
    /**
     * ios设备的idfa
     */
    private String idfa;
    /**
     *
     */
    private String idfa_md5;
    /**
     *
     */
    private String imei;
    /**
     *
     */
    private String imei_md5;
    /**
     *
     */
    private String android_id;
    /**
     *
     */
    private String android_id_md5;
    /**
     *
     */
    private String android_ad_id;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
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

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

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

    public String getAndroid_ad_id() {
        return android_ad_id;
    }

    public void setAndroid_ad_id(String android_ad_id) {
        this.android_ad_id = android_ad_id;
    }
}
