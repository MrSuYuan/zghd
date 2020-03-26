package com.zghd.entity.XiaoZhi;

/**
 * 小知请求参数
 */
public class AdReq {

    /**
     * 请求的唯一 id，32个字节字符串 生成规则： MD5小写值（设 备号 +APP包名 +毫秒时间戳)
     * @requert true
     */
    private String bid;
    /**
     * API 版本
     * @requert true
     */
    private String api_version;
    /**
     * 客户端的 UserAgent
     * @requert true
     */
    private String ua;
    /**
     * app信息
     */
    private App app;
    /**
     * 设备信息
     */
    private Device device;
    /**
     * 广告位信息
     */
    private Adspaces adspaces;
    /**
     * 网络信息
     */
    private Network network;

    /**
     * user
     * @return
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
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

    public Adspaces getAdspaces() {
        return adspaces;
    }

    public void setAdspaces(Adspaces adspaces) {
        this.adspaces = adspaces;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
