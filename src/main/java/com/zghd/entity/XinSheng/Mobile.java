package com.zghd.entity.XinSheng;

/**
 * 手机设备信息
 */
public class Mobile {

    /**
     * 网络运营商类型
     * 0:未知, 1:移动, 2:电信, 3:联通, 99:其他
     */
    private int operator;
    /**
     * 联网方式
     * 0: 其它 1: WIFI, 2: 2G, 3: 3G, 4: 4G
     */
    private int network;
    /**
     * 屏幕密度，一个逻辑像素等于几个实际像素
     */
    private String density;
    /**
     * 屏幕宽。取设备物理像素
     */
    private int width;
    /**
     * 屏幕高。取设备物理像素
     */
    private int height;
    /**
     * 操作系统
     * 1：android系统   2：ios系统
     */
    private int os;
    /**
     * 操作系统版本号
     */
    private String os_version;
    /**
     * 手机设备厂商
     */
    private String vendor;
    /**
     * 手机型号
     */
    private String model;
    /**
     * mac地址
     */
    private String mac;
    /**
     * 安卓设备的android̲id
     */
    private String android_id;
    /**
     * 移动设备ID号
     */
    private String udid;
    /**
     * udid的类型
     * 苹果设备填idfa
     * 安卓设备填imei
     */
    private String identify_type;

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getIdentify_type() {
        return identify_type;
    }

    public void setIdentify_type(String identify_type) {
        this.identify_type = identify_type;
    }
}
