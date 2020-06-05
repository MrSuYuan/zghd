package com.zghd.entity.OPPO;

public class DevInfo {

    //imei和imeiMd5二选一
    private String imei;
    private String imeiMd5;
    //安卓10以上oaid
    private String oaId;
    //暂时不传
    private String vaId;
    //ip
    private String ip;
    //浏览器用user agent 串
    private String ua;
    //mac 和 macMd5 二选一
    private String mac;
    private String macMd5;
    //安卓id
    private String anId;
    //oppo系统版本 非必传
    private String colorOsv;
    //rom版本  非必传
    private String romv;
    //安卓版本号
    private String anVer;
    //屏幕的高
    private int h;
    //屏幕的宽
    private int w;
    //屏幕密度
    private double density;
    //网络连接类型
    private String connectionType;
    //运营商
    private int carrier;
    //屏幕旋转角度
    private int ori;
    //当前地理位置
    private GpsInfo gpsInfo;
    //Wifi 强度
    private int linkSpeed;
    //设备制造商
    private String brand;
    //设备型号
    private String model;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImeiMd5() {
        return imeiMd5;
    }

    public void setImeiMd5(String imeiMd5) {
        this.imeiMd5 = imeiMd5;
    }

    public String getOaId() {
        return oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }

    public String getVaId() {
        return vaId;
    }

    public void setVaId(String vaId) {
        this.vaId = vaId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMacMd5() {
        return macMd5;
    }

    public void setMacMd5(String macMd5) {
        this.macMd5 = macMd5;
    }

    public String getAnId() {
        return anId;
    }

    public void setAnId(String anId) {
        this.anId = anId;
    }

    public String getColorOsv() {
        return colorOsv;
    }

    public void setColorOsv(String colorOsv) {
        this.colorOsv = colorOsv;
    }

    public String getRomv() {
        return romv;
    }

    public void setRomv(String romv) {
        this.romv = romv;
    }

    public String getAnVer() {
        return anVer;
    }

    public void setAnVer(String anVer) {
        this.anVer = anVer;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public int getCarrier() {
        return carrier;
    }

    public void setCarrier(int carrier) {
        this.carrier = carrier;
    }

    public int getOri() {
        return ori;
    }

    public void setOri(int ori) {
        this.ori = ori;
    }

    public GpsInfo getGpsInfo() {
        return gpsInfo;
    }

    public void setGpsInfo(GpsInfo gpsInfo) {
        this.gpsInfo = gpsInfo;
    }

    public int getLinkSpeed() {
        return linkSpeed;
    }

    public void setLinkSpeed(int linkSpeed) {
        this.linkSpeed = linkSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
