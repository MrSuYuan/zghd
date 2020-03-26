package com.zghd.entity.JiGuang;

/**
 * 极光请求参数
 */
public class AdRequest {

    /**
     * 请求id
     * @request false
     */
    private String req_id;
    /**
     * API接口版本 当前版本 1.2.0
     * @request true
     */
    private String api_version;
    /**
     * 支持素材协议类型 http https
     * @request true
     */
    private String protocol;
    /**
     * app参数组
     * @request true
     */
    private App app;
    /**
     * adslot参数组
     * @request true
     */
    private Adslot adslot;
    /**
     * device参数组
     * @request true
     */
    private Device device;
    /**
     * udid参数组
     * @request true
     */
    private Udid udid;
    /**
     * network参数组
     * @request true
     */
    private Network network;
    /**
     * wifi参数组
     * @request false
     */
    private Wifi wifi;
    /**
     * gps参数组
     * @request false
     */
    private Gps gps;

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

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

    public Udid getUdid() {
        return udid;
    }

    public void setUdid(Udid udid) {
        this.udid = udid;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Wifi getWifi() {
        return wifi;
    }

    public void setWifi(Wifi wifi) {
        this.wifi = wifi;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }
}
