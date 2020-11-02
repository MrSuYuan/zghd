package com.zghd.entity.PengTai.request;

public class Device {

    //private Geo geo;
    //private int lmt;
    private String ip;
    private String ipv6;
    private int devicetype;
    private String make;
    private String model;
    private String os;
    private String osv;
    //private String language;
    //private String carrier;
    private int connectiontype;
    private String ifa;
    private DeviceExt ext;
    private String ua;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
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

    public String getIfa() {
        return ifa;
    }

    public void setIfa(String ifa) {
        this.ifa = ifa;
    }

    public DeviceExt getExt() {
        return ext;
    }

    public void setExt(DeviceExt ext) {
        this.ext = ext;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }
}
