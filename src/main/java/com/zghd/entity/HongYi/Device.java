package com.zghd.entity.HongYi;

/**
 * 设备信息
 */
public class Device {

    /**
     * ios的idfa
     */
    private String id_idfa;
    /**
     * 安卓的imei
     */
    private String id_imei;
    /**
     * imsi
     */
    private String id_imsi;
    /**
     * androidId
     */
    private String id_androidid;
    /**
     * mac
     */
    private String id_mac;
    /**
     * 屏幕高度
     */
    private int height;
    /**
     * 屏幕宽度
     */
    private int width;
    /**
     * 设备品牌
     */
    private String brand;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 操作系统 a.b.c
     */
    private String os_version;
    /**
     * 操作系统类型
     * 0未知 1安卓 2iOS 3wp
     */
    private int os_type;

    public String getId_idfa() {
        return id_idfa;
    }

    public void setId_idfa(String id_idfa) {
        this.id_idfa = id_idfa;
    }

    public String getId_imei() {
        return id_imei;
    }

    public void setId_imei(String id_imei) {
        this.id_imei = id_imei;
    }

    public String getId_imsi() {
        return id_imsi;
    }

    public void setId_imsi(String id_imsi) {
        this.id_imsi = id_imsi;
    }

    public String getId_androidid() {
        return id_androidid;
    }

    public void setId_androidid(String id_androidid) {
        this.id_androidid = id_androidid;
    }

    public String getId_mac() {
        return id_mac;
    }

    public void setId_mac(String id_mac) {
        this.id_mac = id_mac;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public int getOs_type() {
        return os_type;
    }

    public void setOs_type(int os_type) {
        this.os_type = os_type;
    }
}
