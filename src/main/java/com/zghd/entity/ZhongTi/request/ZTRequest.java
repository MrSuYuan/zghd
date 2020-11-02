package com.zghd.entity.ZhongTi.request;

/**
 * 中体互联--欧朋浏览器
 */
public class ZTRequest {

    private String token;
    private App app;
    private Device device;
    private Adslot adslot;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Adslot getAdslot() {
        return adslot;
    }

    public void setAdslot(Adslot adslot) {
        this.adslot = adslot;
    }
}
