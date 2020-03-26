package com.zghd.entity.YuLiang;

import java.util.List;

/**
 * y余梁请求实例
 */
public class RequestJson {

    /**
     * 请求ID
     * @request true
     */
    private String request_id;
    /**
     * APP信息
     * @request true
     */
    private App app;
    /**
     * 设备信息
     * @request true
     */
    private Device device;
    /**
     * 网络信息
     * @request true
     */
    private Network network;
    /**
     * 广告位数组
     * @request true
     */
    private List<Imp> imps;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
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

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public List<Imp> getImps() {
        return imps;
    }

    public void setImps(List<Imp> imps) {
        this.imps = imps;
    }
}
