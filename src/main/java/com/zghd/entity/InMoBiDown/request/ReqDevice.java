package com.zghd.entity.InMoBiDown.request;

public class ReqDevice {

    private String connectiontype;
    private int devicetype;
    private String ifa;
    private String didmd5;
    private String dpidmd5;
    private String dpidsha1;
    private ReqGeo geo;
    private String ip;
    private String make;
    private String model;
    private String oaid;
    private String os;
    private String osv;
    private String ua;
    private ReqDeviceExt ext;

    public ReqDeviceExt getExt() {
        return ext;
    }

    public void setExt(ReqDeviceExt ext) {
        this.ext = ext;
    }

    public String getConnectiontype() {
        return connectiontype;
    }

    public void setConnectiontype(String connectiontype) {
        this.connectiontype = connectiontype;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }

    public String getIfa() {
        return ifa;
    }

    public void setIfa(String ifa) {
        this.ifa = ifa;
    }

    public String getDidmd5() {
        return didmd5;
    }

    public void setDidmd5(String didmd5) {
        this.didmd5 = didmd5;
    }

    public String getDpidmd5() {
        return dpidmd5;
    }

    public void setDpidmd5(String dpidmd5) {
        this.dpidmd5 = dpidmd5;
    }

    public String getDpidsha1() {
        return dpidsha1;
    }

    public void setDpidsha1(String dpidsha1) {
        this.dpidsha1 = dpidsha1;
    }

    public ReqGeo getGeo() {
        return geo;
    }

    public void setGeo(ReqGeo geo) {
        this.geo = geo;
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

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
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

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }
}
