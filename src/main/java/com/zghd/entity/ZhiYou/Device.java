package com.zghd.entity.ZhiYou;

import java.util.List;

//设备信息
public class Device {
    //ua
    private String ua;
    //地址信息
    private Geo geo;
    //ip
    private String ip;
    private String ipv6;
    //设备类型 2pc 3tv 4手机 5平板 7机顶盒 0未知
    private int devicetype;
    //生产厂
    private String make;
    //设备型号
    private String model;
    //系统 ios android
    private String os;
    //版本
    private String osv;
    //硬件版本
    private String hwv;
    //分辨率高
    private int h;
    //分辨率宽
    private int w;
    //屏幕宽
    private int sw;
    //屏幕高
    private int sh;
    //像素密度
    private int ppi;
    //屏幕像素密度
    private int dpi;
    //idfa
    private String ifa;
    private String ifamd5;
    private String ifasha1;
    //idfv
    private String ifv;
    private String ifvmd5;
    private String ifvsha1;
    //苹果id
    private String udid;
    private String udidmd5;
    private String udidsha1;
    //imei号
    private String did;
    private String didmd5;
    private String didsha1;
    //安卓广告编号
    private String dpid;
    private String dpidmd5;
    private String dpidsha1;
    //安卓id
    private String oaId;
    private String oaIdmd5;
    private String oaIdsha1;
    //mac地址
    private String mac;
    private String macmd5;
    private String macsha1;
    //运营商 46000移动 46001联通 4600.电信 46020铁通
    private String carrier;
    //网络类型 0未知 1以太网 2wifi 3:2G 4:3G 5:4G 6:5G
    private int connectiontype;
    //imsi
    private String imsi;
    //屏幕方向 0竖屏 1横屏
    private int orientation;
    //本机安装的应用包名集合
    private List<String> apps;
    //默认系统语言
    private String language;

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

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

    public String getHwv() {
        return hwv;
    }

    public void setHwv(String hwv) {
        this.hwv = hwv;
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

    public int getPpi() {
        return ppi;
    }

    public void setPpi(int ppi) {
        this.ppi = ppi;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
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

    public String getIfasha1() {
        return ifasha1;
    }

    public void setIfasha1(String ifasha1) {
        this.ifasha1 = ifasha1;
    }

    public String getIfv() {
        return ifv;
    }

    public void setIfv(String ifv) {
        this.ifv = ifv;
    }

    public String getIfvmd5() {
        return ifvmd5;
    }

    public void setIfvmd5(String ifvmd5) {
        this.ifvmd5 = ifvmd5;
    }

    public String getIfvsha1() {
        return ifvsha1;
    }

    public void setIfvsha1(String ifvsha1) {
        this.ifvsha1 = ifvsha1;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getUdidmd5() {
        return udidmd5;
    }

    public void setUdidmd5(String udidmd5) {
        this.udidmd5 = udidmd5;
    }

    public String getUdidsha1() {
        return udidsha1;
    }

    public void setUdidsha1(String udidsha1) {
        this.udidsha1 = udidsha1;
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

    public String getDidsha1() {
        return didsha1;
    }

    public void setDidsha1(String didsha1) {
        this.didsha1 = didsha1;
    }

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid;
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

    public String getOaId() {
        return oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }

    public String getOaIdmd5() {
        return oaIdmd5;
    }

    public void setOaIdmd5(String oaIdmd5) {
        this.oaIdmd5 = oaIdmd5;
    }

    public String getOaIdsha1() {
        return oaIdsha1;
    }

    public void setOaIdsha1(String oaIdsha1) {
        this.oaIdsha1 = oaIdsha1;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMacmd5() {
        return macmd5;
    }

    public void setMacmd5(String macmd5) {
        this.macmd5 = macmd5;
    }

    public String getMacsha1() {
        return macsha1;
    }

    public void setMacsha1(String macsha1) {
        this.macsha1 = macsha1;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getConnectiontype() {
        return connectiontype;
    }

    public void setConnectiontype(int connectiontype) {
        this.connectiontype = connectiontype;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public List<String> getApps() {
        return apps;
    }

    public void setApps(List<String> apps) {
        this.apps = apps;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
