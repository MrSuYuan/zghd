package com.zghd.entity.JuLiang;

/**
 * 请求数据
 */
public class RequestJson {

    /**
     * app
     */
    private App app;
    /**
     * device
     */
    private Device device;
    /**
     * geo
     */
    private Geo geo;
    /**
     * 广告宽
     */
    private int adw;
    /**
     * 广告高
     */
    private int adh;
    /**
     * 应用ID
     */
    private String appid;
    /**
     * 广告形式(取值范围：
     * banner(横幅)、interst(插屏)、splash(闪屏或全屏)、video(视频)、feed(信息流、fvideo(信息流视频))
     */
    private String slotid;
    /**
     * 渠道
     */
    private String chn;
    /**
     * 终端设备外网的IP(服务器发送请求IP 字段必传)
     */
    private String ip;
    /**
     * 0/1/2: 未知/横屏/竖屏
     */
    private int dso;
    /**
     * IOS 时用到，取值0 或1，1 表示支持https
     */
    private int ishttps;

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

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public int getAdw() {
        return adw;
    }

    public void setAdw(int adw) {
        this.adw = adw;
    }

    public int getAdh() {
        return adh;
    }

    public void setAdh(int adh) {
        this.adh = adh;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSlotid() {
        return slotid;
    }

    public void setSlotid(String slotid) {
        this.slotid = slotid;
    }

    public String getChn() {
        return chn;
    }

    public void setChn(String chn) {
        this.chn = chn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getDso() {
        return dso;
    }

    public void setDso(int dso) {
        this.dso = dso;
    }

    public int getIshttps() {
        return ishttps;
    }

    public void setIshttps(int ishttps) {
        this.ishttps = ishttps;
    }
}
