package com.zghd.entity.YongQi;

/**
 * 设备信息
 */
public class Device {

    /**
     * 是否允许广告追踪  0允许  1不允许
     */
    private String dnt;
    /**
     * 浏览器ua
     */
    private String ua;
    /**
     * 设备ip地址
     */
    private String ip;
    /**
     * 地理信息对象
     */
    private Geo geo;
    /**
     * imei的md5值
     */
    private String didsmd5;
    /**
     * mac地址的md5值
     */
    private String macmd5;
    /**
     * android 或 idfa 的md5值
     */
    private String dpidmd5;
    /**
     * android 或 idfa 的原始值
     */
    private String dpid;
    /**
     * 设备制造商
     */
    private String make;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 设备操作系统
     */
    private String os;
    /**
     * 操作系统版本号
     */
    private String osv;
    /**
     * 设备联网类型
     * 0位置  1以太网  2wifi  3位置蜂窝网络  4:2g  5:3g  6:4g
     */
    private int connectiontype;
    /**
     * 运营商名称
     * wnknow:位置   ChinaMobile:中国移动  ChinaUnicom:中国联通  ChinaTelecom:中国电信
     */
    private String carrier;
    /**
     * 设备屏幕像素密度
     */
    private float s_density;
    /**
     * 设备屏幕分辨率宽度 px
     */
    private int sw;
    /**
     * 设备屏幕分辨率高度 px
     */
    private int sh;
    /**
     * 扩展内容
     */
    private Extension ext;

    public String getDnt() {
        return dnt;
    }

    public void setDnt(String dnt) {
        this.dnt = dnt;
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

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getDidsmd5() {
        return didsmd5;
    }

    public void setDidsmd5(String didsmd5) {
        this.didsmd5 = didsmd5;
    }

    public String getMacmd5() {
        return macmd5;
    }

    public void setMacmd5(String macmd5) {
        this.macmd5 = macmd5;
    }

    public String getDpidmd5() {
        return dpidmd5;
    }

    public void setDpidmd5(String dpidmd5) {
        this.dpidmd5 = dpidmd5;
    }

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid;
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsv() {
        return osv;
    }

    public void setOsv(String osv) {
        this.osv = osv;
    }

    public int getConnectiontype() {
        return connectiontype;
    }

    public void setConnectiontype(int connectiontype) {
        this.connectiontype = connectiontype;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public float getS_density() {
        return s_density;
    }

    public void setS_density(float s_density) {
        this.s_density = s_density;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public int getSh() {
        return sh;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }

    public Extension getExt() {
        return ext;
    }

    public void setExt(Extension ext) {
        this.ext = ext;
    }
}
