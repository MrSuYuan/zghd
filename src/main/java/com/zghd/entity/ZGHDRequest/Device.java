package com.zghd.entity.ZGHDRequest;

/**
 * 设备信息
 */
public class Device {

    /**
     * IOS设备唯一标识码
     */
    private String idfa;
    /**
     * 安卓设备唯一标识码
     */
    private String oaid;
    private String imei;
    private String imei_md5;
    /**
     * MAC地址
     */
    private String mac;
    /**
     * 安卓设备ID
     */
    private String androidId;
    /**
     * 操作系统类型
     * 1-Android   2-iOS
     */
    private int osType;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * 设备类型
     * 1-手机  2-平板
     */
    private int deviceType;
    /**
     * 设备厂商
     */
    private String vendor;
    /**
     * 手机品牌
     */
    private String brand;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 设备屏幕宽度
     */
    private int screenWidth;
    /**
     * 设备屏幕高度
     */
    private int screenHeight;
    /**
     * User-agent
     */
    private String ua;
    /**
     * 屏幕大小(单位ppi,每英寸所有的像素)
     */
    private int ppi;
    /**
     * 横竖屏
     * 0-未知  1-竖屏  2-横屏
     */
    private int screenOrientation;
    /**
     * imsi
     */
    private String imsi;

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getImei_md5() {
        return imei_md5;
    }

    public void setImei_md5(String imei_md5) {
        this.imei_md5 = imei_md5;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public int getPpi() {
        return ppi;
    }

    public void setPpi(int ppi) {
        this.ppi = ppi;
    }

    public int getScreenOrientation() {
        return screenOrientation;
    }

    public void setScreenOrientation(int screenOrientation) {
        this.screenOrientation = screenOrientation;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
