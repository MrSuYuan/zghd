package com.zghd.entity.VIVO.request;

/**
 * 设备信息
 */
public class Device {
    /**
     * mac
     */
    private String mac;
    /**
     * imei
     */
    private String imei;
    /**
     * didMd5(imei的md5小写)
     */
    private String didMd5;
    /**
     * oaid
     */
    private String oaid;
    /**
     * vaid
     */
    private String vaid;
    /**
     * androidId
     */
    private String androidId;
    /**
     * 安卓版本
     */
    private String an;
    /**
     * 安卓系统版本号
     * 安卓API等级。示例：23。 av >= 29时，视为安卓系统为Android Q及以上
     */
    private int av;
    /**
     * ua
     */
    private String ua;
    /**
     * ip
     */
    private String ip;
    /**
     * 厂商
     */
    private String make;
    /**
     * 手机型号
     */
    private String model;
    /**
     * 语言 zh-CN
     */
    private String Language;
    /**
     * 屏幕宽
     */
    private int screenWidth;
    /**
     * 屏幕高
     */
    private int screenHeight;
    /**
     * 屏幕ppi
     */
    private int ppi;
    /**
     * 开机时长
     */
    private long elapseTime;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDidMd5() {
        return didMd5;
    }

    public void setDidMd5(String didMd5) {
        this.didMd5 = didMd5;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getVaid() {
        return vaid;
    }

    public void setVaid(String vaid) {
        this.vaid = vaid;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public int getAv() {
        return av;
    }

    public void setAv(int av) {
        this.av = av;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getPpi() {
        return ppi;
    }

    public void setPpi(int ppi) {
        this.ppi = ppi;
    }

    public long getElapseTime() {
        return elapseTime;
    }

    public void setElapseTime(long elapseTime) {
        this.elapseTime = elapseTime;
    }
}
