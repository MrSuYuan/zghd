package com.zghd.entity.YouKu.request;

/**
 * 设备信息
 */
public class Device {

    //user agent
    private String ua;
    //IP
    private String ip;
    //使用MD5哈希的Device ID，在移动流量里，这个字段是IMEI的md5值（废弃，不推荐使用）
    private String didmd5;
    //操作系统，取值如下： Windows: "Windows",  Android: "Android",  iPhone: "ios",  苹果电脑: "Mac"
    private String os;
    //操作系统版本号，如"4.1", "XP"等
    private String osv;
    //设备类型，和0—手机，1—平板，2—PC，3—互联网电视。
    private int devicetype;
    //当os = ‘ios’时有效, 明文传输，默认为空字符串
    private String idfa;
    //Android Q设备id
    private String oaid;
    //当os = ‘android’时有效，明文传输，默认为空字符串（已废弃，协议还会下发，值为空）
    private String androidid;
    //IMEI, 明文传输，默认为空字符串（已废弃，协议还会下发，值为空）
    private String imei;
    //MAC地址，明文传输，默认为空字符串（已废弃，协议还会下发，值为空）
    private String mac;
    //当os = ‘android’时有效，传输明文md5值，默认为空字符串
    private String androidid_md5;
    //IMEI, 传输明文md5值，默认为空字符串。注：Android Q版本无法获取
    private String imei_md5;
    //MAC地址，传输明文md5值，默认为空字符串
    private String mac_md5;
    //制造厂商,如“apple”“Samsung”“Huawei“，默认为空字符串
    private String make;
    //型号, 如”iphoneA1530”，默认为空字符串
    private String model;
    //链接类型, 0：未知; 1：以太网2：Wifi; 3：移动数据 -未知; 4：2G; 5:3G
    private int connectiontype;
    //运营商, 0：wifi；1：中国移动；2：中国联通：3：中国电信；4：其他；5：未识别
    private String carrier;
    //屏幕宽度, 像素 ，默认为空字符串
    private String screenwidth;
    //屏幕高度, 像素 ，默认为空字符串
    private String screenhight;

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

    public String getDidmd5() {
        return didmd5;
    }

    public void setDidmd5(String didmd5) {
        this.didmd5 = didmd5;
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

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAndroidid_md5() {
        return androidid_md5;
    }

    public void setAndroidid_md5(String androidid_md5) {
        this.androidid_md5 = androidid_md5;
    }

    public String getImei_md5() {
        return imei_md5;
    }

    public void setImei_md5(String imei_md5) {
        this.imei_md5 = imei_md5;
    }

    public String getMac_md5() {
        return mac_md5;
    }

    public void setMac_md5(String mac_md5) {
        this.mac_md5 = mac_md5;
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

    public String getScreenwidth() {
        return screenwidth;
    }

    public void setScreenwidth(String screenwidth) {
        this.screenwidth = screenwidth;
    }

    public String getScreenhight() {
        return screenhight;
    }

    public void setScreenhight(String screenhight) {
        this.screenhight = screenhight;
    }
}
