package com.zghd.entity.YiDianTong;

/**
 * 一点通请求数据
 */
public class RequestJson {

    /**
     * 请求ID
     * @request true
     */
    private String requestId;
    /**
     * 设备相关信息
     * @request true
     */
    private Device device;
    /**
     * 网络相关信息
     * @request true
     */
    private Network network;
    /**
     * 代码位id
     * @request true
     */
    private String channelId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
