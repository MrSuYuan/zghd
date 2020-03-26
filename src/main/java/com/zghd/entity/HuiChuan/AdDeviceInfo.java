package com.zghd.entity.HuiChuan;

/**
 * 设备信息
 */
public class AdDeviceInfo {
    //安卓id
    private String android_id;
    //(淘内推荐填写)设备ID，Android取imei，IOS按客户端现有逻辑获取；如果获取不到imei，则传递androdid的值；
    private String devid;
    //imei
    private String imei;
    //Android设备相关id
    private String oaid;
    //ios备选设备ID
    private String udid;
    //ios备选设备ID
    private String open_udid;
    //ios设备ID，仅IOS 6+有效，用于ios设备的用户识别和兴趣投放
    private String idfa;
    //设备型号（需要归一化所有的设备型号），直接填写用户的设备类型即可，目前没有归一化
    private String device;
    //操作系统，枚举选项， android/ios/wp，用于操作系统定向投放
    private String os;
    //操作系统版本（需要归一化所有的设备型号）
    private String osv;
    //cpu型号
    private String cpu;
    //mac 地址（小写归一化）
    private String mac;
    //+++屏幕宽度（物理分辨率）
    private String sw;
    //+++屏幕高度（物理分辨率）
    private String sh;
    //+++系统是否越狱（Android 取是否 root，IOS 取是否越狱）枚举值: 0 不确定、1 越狱、2 未越狱，用于定向投放
    private String is_jb;
    //网络类型 枚举值 Wi-Fi/2G/3G/4G/Unknown，用于网络定向投放
    private String access;
    //运营商枚举值：Unknown、ChinaMobil、ChinaUnicom、ChinaTelecom、ChinaTietong
    private String carrier;
    //cp信息，格式如：isp:电信;prov:广东;city:广州;na:中国;cc:CN;ac:，用于获取用户地域信息，如果实在无法获取，可以不填
    private String cp;
    //(淘内)阿里统一设备id，强烈建议填充，用于用户识别和兴趣投放
    private String aid;
    //服务端转发必须填充该字段，客户端直连不需要填充，用于用户地域识别，广告地域投放和反作弊
    private String client_ip;

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getOpen_udid() {
        return open_udid;
    }

    public void setOpen_udid(String open_udid) {
        this.open_udid = open_udid;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getIs_jb() {
        return is_jb;
    }

    public void setIs_jb(String is_jb) {
        this.is_jb = is_jb;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }
}
