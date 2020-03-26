package com.zghd.entity.WangMai;

/**
 * 旺脉请求data
 */
public class Data {

    /**
     * app信息
     */
    private App app;
    /**
     * 代码位信息
     */
    private Adslot adslot;
    /**
     * 设备信息
     */
    private Device device;
    /**
     * 网络信息
     */
    private Network network;
    /**
     * gps信息
     */
    private Gps gps;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Adslot getAdslot() {
        return adslot;
    }

    public void setAdslot(Adslot adslot) {
        this.adslot = adslot;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }
}
