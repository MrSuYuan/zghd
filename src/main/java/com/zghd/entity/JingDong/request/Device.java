package com.zghd.entity.JingDong.request;

public class Device {

    //android/ios
    private String os;
    //操作系统版本
    private String osv;
    //imei
    private String did;
    private String didmd5;
    //oaid
    private String oid;
    //ios的ifa
    private String ifa;
    private String ifamd5;
    //mac
    private String macidmd5;
    //ip
    private String ip;
    //ua
    private String ua;
    //0unknow 1ethernet 2wifi 42G 53G 64G
    private int connectiontype;
    //设备制造商
    private String make;
    //设备型号
    private String model;
    //型号版本
    private String hwv;
    //mobile移动  unicom联通  telecom电信
    private String carrier;
    //屏幕高
    private int screenheight;
    //屏幕宽
    private int screenwidth;
    //ppi
    private int ppi;
    //地理位置
    private Geo geo;
    //扩展字段
    private Ext ext;

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

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDidmd5() {
        return didmd5;
    }

    public void setDidmd5(String didmd5) {
        this.didmd5 = didmd5;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getIfa() {
        return ifa;
    }

    public void setIfa(String ifa) {
        this.ifa = ifa;
    }

    public String getIfamd5() {
        return ifamd5;
    }

    public void setIfamd5(String ifamd5) {
        this.ifamd5 = ifamd5;
    }

    public String getMacidmd5() {
        return macidmd5;
    }

    public void setMacidmd5(String macidmd5) {
        this.macidmd5 = macidmd5;
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

    public int getConnectiontype() {
        return connectiontype;
    }

    public void setConnectiontype(int connectiontype) {
        this.connectiontype = connectiontype;
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

    public String getHwv() {
        return hwv;
    }

    public void setHwv(String hwv) {
        this.hwv = hwv;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getScreenheight() {
        return screenheight;
    }

    public void setScreenheight(int screenheight) {
        this.screenheight = screenheight;
    }

    public int getScreenwidth() {
        return screenwidth;
    }

    public void setScreenwidth(int screenwidth) {
        this.screenwidth = screenwidth;
    }

    public int getPpi() {
        return ppi;
    }

    public void setPpi(int ppi) {
        this.ppi = ppi;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }
}
