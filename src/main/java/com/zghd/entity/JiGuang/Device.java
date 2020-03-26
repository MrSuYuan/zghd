package com.zghd.entity.JiGuang;

/**
 * 设备参数信息
 */
public class Device {

    /**
     * 设备类型  PHONE 手机设备  ,  PAD 平板设备
     * @request true
     */
    private String type;
    /**
     * 操作系统 iOS , Android
     * @request true
     */
    private String os_type;
    /**
     * 操作系统版本
     * @request true
     */
    private String os_version;
    /**
     * 设备厂商
     * @request true
     */
    private String vendor;
    /**
     * 设备型号
     * @request true
     */
    private String model;
    /**
     * 设备屏幕高
     * @request true
     */
    private int screen_height;
    /**
     * 设备屏幕宽
     * @request true
     */
    private int screen_width;
    /**
     * User-Agent
     * @request true
     */
    private String ua;
    /**
     * 屏幕状态
     * @request true
     */
    //private ScreenState screen_state;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
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

    public int getScreen_height() {
        return screen_height;
    }

    public void setScreen_height(int screen_height) {
        this.screen_height = screen_height;
    }

    public int getScreen_width() {
        return screen_width;
    }

    public void setScreen_width(int screen_width) {
        this.screen_width = screen_width;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }
}
