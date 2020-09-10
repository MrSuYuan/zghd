package com.zghd.entity.JuMai.request;

/**
 * 俱脉设备信息
 */
public class Device {

    //设备 IP，必须是客户端真实获取 IP
    private String ip;
    //网络类型，0：未知，1：wifi，2：2G，3：3G，4：4G
    private int net;
    //网络运营商 ，46000：中国移动、46001：中国联通、46003：中国电信
    private int carrier;
    //浏览器 ua ,必须是客户端系统的 UA
    private String ua;
    //设备类型，（0 未知、1 phone、 2 pad、 3pc、4 wap）
    private int type;
    //客户端操作系，1：Android、 2 ：IOS、
    private int os;
    //系统版本
    private String osv;
    //移动设备身份码
    private String imsi;
    //安卓 imei
    private String imei;
    //安卓 ID
    private String aid;
    //设备的 MAC 地址
    private String mac;
    //IOS 的 idfv
    private String idfv;
    //IOS 的 idfa
    private String idfa;
    //ios 中的 openudid
    private String oid;
    //设备品牌
    private String brand;
    //设备型号
    private String model;
    //屏幕密度
    private double density;
    //屏幕宽度
    private int sw;
    //屏幕高度
    private int sh;
    //横竖屏 0：未知、1：竖屏、2：横屏
    private int so;
    //经度
    private double lon;
    //纬度
    private double lat;
    //安卓10 ID，获取不到imei时必填
    private String oaid;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getCarrier() {
        return carrier;
    }

    public void setCarrier(int carrier) {
        this.carrier = carrier;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public String getOsv() {
        return osv;
    }

    public void setOsv(String osv) {
        this.osv = osv;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
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

    public int getSo() {
        return so;
    }

    public void setSo(int so) {
        this.so = so;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }
}
