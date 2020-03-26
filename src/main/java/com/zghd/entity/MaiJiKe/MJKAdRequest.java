package com.zghd.entity.MaiJiKe;

/**
 * 迈吉客广告请求参数
 */
public class MJKAdRequest {
 /*MJKAdRequest mar = new MJKAdRequest();
        mar.setAid(50013);
        mar.setSid(5181);
        mar.setDevicetype(1);
        mar.setOstype(1);
        mar.setImei("865376033710664");
        mar.setIdfa("");
        mar.setIdfv("");
        mar.setImsi("-1");
        mar.setMac("DC:72:9B:7B:30:7A");
        mar.setAdrid("562d4ba76d63aa9f");
        mar.setOpid("");
        mar.setOsv("7.0");
        mar.setDv("Samsung");
        mar.setDm("SM-J7008");
        mar.setNt("0");
        mar.setSw(1080);
        mar.setSh(1920);
        mar.setW(1080);
        mar.setH(1920);
        mar.setIp("172.28.60.107");
        mar.setUseragent("Mozilla/5.0 (Linux; Android 9; BND-AL10 Build/HONORBND-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36");
        mar.setSn("-1");
        mar.setSon(1);
        mar.setLac("");
        mar.setCellularid("");
        mar.setLat(0);
        mar.setLon(0);
        return JSONObject.fromObject(mar).toString();*/
    /**
     * appid
     */
    private int aid;
    /**
     * 广告位id
     */
    private int sid;
    /**
     * 设备类型 1-手机 2-平板
     */
    private int devicetype;
    /**
     * 设备系统 1-安卓 2-IOS
     */
    private int ostype;
    /**
     * 安卓系统必填 手机imei
     */
    private String imei;
    /**
     * IOS系统必填
     */
    private String idfa;
    /**
     * IOS系统必填
     */
    private String idfv;
    /**
     * 手机sim卡 ,没有时传-1
     */
    private String imsi;
    /**
     * 手机设备mac地址
     */
    private String mac;
    /**
     * 安卓ID
     */
    private String adrid;
    /**
     * 运营商ID
     */
    private String opid;
    /**
     * 系统版本 例如7.0.1
     */
    private String osv;
    /**
     * device制造商
     */
    private String dv;
    /**
     * Device型号
     */
    private String dm;
    /**
     * 网络类型
     */
    private String nt;
    /**
     * 屏幕宽
     */
    private int sw;
    /**
     * 屏幕高
     */
    private int sh;
    /**
     * 广告位宽
     */
    private int w;
    /**
     * 广告位高
     */
    private int h;
    /**
     * 客户端ip
     */
    private String ip;
    /**
     * UA
     */
    private String useragent;
    /**
     * 手机sn号
     */
    private String sn;
    /**
     * 屏幕方向 1-竖屏 2-横屏
     */
    private int son;
    /**
     * 基站位置区域码
     */
    private String lac;
    /**
     * 基站编码
     */
    private String cellularid;
    /**
     * 经度
     */
    private double lat;
    /**
     * 纬度
     */
    private double lon;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }

    public int getOstype() {
        return ostype;
    }

    public void setOstype(int ostype) {
        this.ostype = ostype;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAdrid() {
        return adrid;
    }

    public void setAdrid(String adrid) {
        this.adrid = adrid;
    }

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public String getOsv() {
        return osv;
    }

    public void setOsv(String osv) {
        this.osv = osv;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getSon() {
        return son;
    }

    public void setSon(int son) {
        this.son = son;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getCellularid() {
        return cellularid;
    }

    public void setCellularid(String cellularid) {
        this.cellularid = cellularid;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
