package com.zghd.entity.HongYi;

/**
 * 虹益请求参数
 */
public class HYRequest {

    /**
     * 媒体来源信息
     */
    private Media media;
    /**
     * 设备信息
     */
    private Device device;
    /**
     * 网络信息
     */
    private Network network;
    /**
     * 广告客户端类型
     */
    private Client client;
    /**
     * 广告位信息
     */
    private Adslot adslot;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Adslot getAdslot() {
        return adslot;
    }

    public void setAdslot(Adslot adslot) {
        this.adslot = adslot;
    }
}
