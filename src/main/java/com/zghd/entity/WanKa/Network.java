package com.zghd.entity.WanKa;

import java.util.List;

/**
 * 网络环境信息
 */
public class Network {

    /**
     * 用户外网ip
     * @required true
     */
    private String ip;
    /**
     * 网络连接类型 0：未知连接，1：以太网，2：WiFi， 3：未知蜂窝网络，4:2G，5:3G，6:4G
     * @required true
     */
    private int connect_type;
    /**
     * 运营商类型 0：未知运营商，1：中国移动，2：中国电信，3：中国联通，4：其他运营商
     * @required true
     */
    private int carrier;
    /**
     * 当前连接的运营商基站id，用于辅助用户定位
     * @required false
     */
    private String cellular_id;
    /**
     * wifi地址（激励视频必填）
     * @required false
     */
    private String bss_id;
    /**
     * 基站位置区域码
     * @required false
     */
    private String lac;
    /**
     * 移动国家代码
     * @required false
     */
    private String mcc;
    /**
     * wifi网络id
     * @required false
     */
    private String netwk_id;
    /**
     * 局域网名称（激励视频必填）
     * @required false
     */
    private String ssid;
    /**
     * wifi连接速度
     * @required false
     */
    private int lksd;
    /**
     * 手机接收信号强度(值为负值)
     * @required false
     */
    private int rssi;
    /**
     * 是否漫游。0：否，1：是
     * @required false
     */
    private int roaming;
    /**
     * 基站信息
     * @required false
     */
    List<BaseStation> stbif;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getConnect_type() {
        return connect_type;
    }

    public void setConnect_type(int connect_type) {
        this.connect_type = connect_type;
    }

    public int getCarrier() {
        return carrier;
    }

    public void setCarrier(int carrier) {
        this.carrier = carrier;
    }

    public String getCellular_id() {
        return cellular_id;
    }

    public void setCellular_id(String cellular_id) {
        this.cellular_id = cellular_id;
    }

    public String getBss_id() {
        return bss_id;
    }

    public void setBss_id(String bss_id) {
        this.bss_id = bss_id;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getNetwk_id() {
        return netwk_id;
    }

    public void setNetwk_id(String netwk_id) {
        this.netwk_id = netwk_id;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getLksd() {
        return lksd;
    }

    public void setLksd(int lksd) {
        this.lksd = lksd;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getRoaming() {
        return roaming;
    }

    public void setRoaming(int roaming) {
        this.roaming = roaming;
    }

    public List<BaseStation> getStbif() {
        return stbif;
    }

    public void setStbif(List<BaseStation> stbif) {
        this.stbif = stbif;
    }
}
