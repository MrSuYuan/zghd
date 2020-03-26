package com.zghd.entity.JuLiang;

/**
 * 设备信息
 */
public class Device {

    /**
     * iOS IDFA 值
     */
    private String idfa;
    /**
     * 第一个IMEI
     */
    private String ime;
    /**
     * 第二个IMEI
     */
    private String ime2;
    /**
     * 安卓匿名设备标识符
     */
    private String oaid;
    /**
     * IMSI
     */
    private String icc;
    /**
     * ICCID
     */
    private String iccid;
    /**
     * 1:Android，2:iOS，3:PC/WAP
     */
    private int plt;
    /**
     * 1:手机，2: pad，3: PC，4:其它
     */
    private String dvt;
    /**
     * 操作系统版本号
     */
    private String ov;
    /**
     * 屏幕密度
     */
    private String dpi;
    /**
     * 屏幕宽
     */
    private String swidth;
    /**
     * 屏幕高
     */
    private String sheight;
    /**
     * 机型
     */
    private String mdl;
    /**
     * 品牌
     */
    private String brd;
    /**
     * Android 系统Google 官方唯一标识
     */
    private String aid;
    /**
     * 语言
     */
    private String lg;
    /**
     * 网络类型0:Unknown，1:WIIF，2:2G，3:3G，4:4G，5:G
     */
    private int net;
    /**
     * 运营商代码460 开头， 如46000 是移动的一种等
     */
    private String opt;
    /**
     * iOS 设备的IDV 值
     */
    private String idfv;
    /**
     * MAC 地址
     */
    private String mac;
    /**
     * WiFi BSSID
     */
    private String bssid;
    /**
     * WiFi 名称
     */
    private String ssid;
    /**
     * User-Agent
     */
    private String ua;

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getIme2() {
        return ime2;
    }

    public void setIme2(String ime2) {
        this.ime2 = ime2;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getIcc() {
        return icc;
    }

    public void setIcc(String icc) {
        this.icc = icc;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public int getPlt() {
        return plt;
    }

    public void setPlt(int plt) {
        this.plt = plt;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getOv() {
        return ov;
    }

    public void setOv(String ov) {
        this.ov = ov;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getSwidth() {
        return swidth;
    }

    public void setSwidth(String swidth) {
        this.swidth = swidth;
    }

    public String getSheight() {
        return sheight;
    }

    public void setSheight(String sheight) {
        this.sheight = sheight;
    }

    public String getMdl() {
        return mdl;
    }

    public void setMdl(String mdl) {
        this.mdl = mdl;
    }

    public String getBrd() {
        return brd;
    }

    public void setBrd(String brd) {
        this.brd = brd;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }
}
