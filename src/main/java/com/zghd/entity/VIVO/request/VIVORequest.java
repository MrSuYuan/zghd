package com.zghd.entity.VIVO.request;

/**
 * vivo请求参数
 */
public class VIVORequest {

    /**
     * 协议版本，当前版本 1.0
     */
    private String apiVersion;
    /**
     * vivo系统ROM版本号无法获取可填写 unknow
     */
    private String sysVersion;
    /**
     * 应用商店版本号
     */
    private int appstoreVersion;
    /**
     * 媒体信息
     */
    private Media media;
    /**
     * 广告位信息
     */
    private Postion postion;
    /**
     * 设备信息
     */
    private Device device;
    /**
     * 网络信息
     */
    private Network network;
    /**
     * 位置信息
     */
    private Geo geo;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public int getAppstoreVersion() {
        return appstoreVersion;
    }

    public void setAppstoreVersion(int appstoreVersion) {
        this.appstoreVersion = appstoreVersion;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Postion getPostion() {
        return postion;
    }

    public void setPostion(Postion postion) {
        this.postion = postion;
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

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
