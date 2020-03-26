package com.zghd.entity.WanKa;

/**
 * 广告请求字段
 */
public class Reqjson {

    /**
     * 当前版本：3.0.0
     * @required true
     */
    private String api_version;
    /**
     * 应用信息
     * @required true
     */
    private App app;
    /**
     * 广告位信息
     * @required true
     */
    private Adslot adslot;
    /**
     * 设备信息
     * @required true
     */
    private Device device;
    /**
     * 网络环境信息
     * @required true
     */
    private Network network;
    /**
     * 定位信息
     * @required true
     */
    private Gps gps;

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
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
