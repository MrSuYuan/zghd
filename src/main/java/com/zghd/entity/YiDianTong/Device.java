package com.zghd.entity.YiDianTong;

/**
 * 设备信息
 */
public class Device {

    /**
     * IOS 设备唯一标识码
     * @request true
     */
    private String idfa;
    /**
     * 安卓设备唯一标识码
     * @request true
     */
    private String imei;
    /**
     * 设备Wifi 网卡MAC 地址
     * @request true
     */
    private String mac;
    /**
     * 安卓设备ID
     * @request true
     */
    private String androidId;
    /**
     * 设备型号
     * @request true
     */
    private String model;
    /**
     * 设备厂商
     * @request true
     */
    private String vendor;
    /**
     * 设备屏幕宽度
     * @request true
     */
    private int screenWidth;
    /**
     * 设备屏幕高度
     * @request true
     */
    private int screenHeight;
    /**
     * 操作系统类型  1安卓  2ios
     * @request true
     */
    private int osType;
    /**
     * 操作系统版本
     * @request true
     */
    private String osVersion;
    /**
     * 设备类型  1手机  2平板
     * @request true
     */
    private int deviceType;
    /**
     * User-agent
     * @request true
     */
    private String ua;
    /**
     * 屏幕大小(单位ppi,每英寸所有的像素)
     * @request true
     */
    private int ppi;
    /**
     * 横竖屏，0：未知，1：竖屏，2：横屏
     * @request true
     */
    private int screenOrientation;
    /**
     * 手机品牌
     * @request true
     */
    private String brand;
    /**
     * imsi 如不填写可能会影响变现能力
     * @request false
     */
    private String imsi;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
