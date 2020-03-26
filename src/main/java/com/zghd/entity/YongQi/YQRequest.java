package com.zghd.entity.YongQi;

import java.util.List;

/**
 * 甬祺请求数据
 */
public class YQRequest {

    /**
     * 请求id
     */
    private String id;
    /**
     * 曝光对象
     */
    private List<Impression> imp;
    /**
     * app信息
     */
    private App app;
    /**
     * 设备信息
     */
    private Device device;
    /**
     * 竞标方式
     * 1: 明拍 2: 暗拍 (第二价胜出)
     */
    private int at;
    /**
     * 1cpm  2cpc
     */
    private int paymode;
    /**
     * 是否是原生app
     */
    private boolean is_native_app;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Impression> getImp() {
        return imp;
    }

    public void setImp(List<Impression> imp) {
        this.imp = imp;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getPaymode() {
        return paymode;
    }

    public void setPaymode(int paymode) {
        this.paymode = paymode;
    }

    public boolean isIs_native_app() {
        return is_native_app;
    }

    public void setIs_native_app(boolean is_native_app) {
        this.is_native_app = is_native_app;
    }
}


