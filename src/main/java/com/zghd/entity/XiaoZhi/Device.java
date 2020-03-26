package com.zghd.entity.XiaoZhi;

import java.util.List;

/**
 * 设备信息
 */
public class Device {

    /**
     * deviceId
     * @request true
     */
    private List<DeviceId> device_id;
    /**
     * 操作系统类型 0未知 1iOS 2android 3windows
     * @request true
     */
    private int os_type;
    /**
     * 操作系统版本号，需从系统API获取的原始数据
     * @request true
     */
    private String os_version;
    /**
     * 设备品牌 iOS使用默认值apple
     * @request true
     */
    private String brand;
    /**
     * 设备型号，Android取Build.MODEL
     * @request true
     */
    private String model;
    /**
     * 设备类型 0未知 1平板 2手机
     * @request true
     */
    private int device_type;
    /**
     * 设备设置语言 zh_cn
     * @request true
     */
    private String language;
    /**
     * 设备屏幕的宽度
     * @request true
     */
    private int screen_width;
    /**
     * 设备屏幕的高度
     * @request true
     */
    private int screen_height;
    /**
     * 屏幕密度
     * @request true
     */
    private double screen_density;
    /**
     * 屏幕朝向 0未知 1竖屏 2横屏
     * @request true
     */
    private int screen_orientation;

    public List<DeviceId> getDevice_id() {
        return device_id;
    }

    public void setDevice_id(List<DeviceId> device_id) {
        this.device_id = device_id;
    }

    public int getOs_type() {
        return os_type;
    }

    public void setOs_type(int os_type) {
        this.os_type = os_type;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
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

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public double getScreen_density() {
        return screen_density;
    }

    public void setScreen_density(double screen_density) {
        this.screen_density = screen_density;
    }

    public int getScreen_orientation() {
        return screen_orientation;
    }

    public void setScreen_orientation(int screen_orientation) {
        this.screen_orientation = screen_orientation;
    }
}
