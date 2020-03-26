package com.zghd.entity.XiaoZhi;

import java.util.List;

/**
 * 网络环境信息
 */
public class Network {

    /**
     * 服务器对接必传，为客户端真实IP
     * @request true
     */
    private String ip;
    /**
     * 网络类型
     * 0  //未知
     * 1  //wifi
     * 2  //2G
     * 3  //3G
     * 4  //4G
     * 必须区分WiFi和移动网络，如果不能区分234G，请统一填写2G
     * @request true
     */
    private int network_type;
    /**
     * 运营商编码
     * 0;  //未知
     * 70120;  //中国移动
     * 70121;  //中国电信
     * 70123;  //中国联通
     * @request true
     */
    private String carrier_id;
    /**
     * 基站ID，用于快速用户定位
     * @request false
     */
    private Object cellular_id;
    /**
     * 周边WiFi 热点列表，用于精细用户定位
     * @request false
     */
    private List<Object> wifi_aps;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(int network_type) {
        this.network_type = network_type;
    }

    public String getCarrier_id() {
        return carrier_id;
    }

    public void setCarrier_id(String carrier_id) {
        this.carrier_id = carrier_id;
    }

    public Object getCellular_id() {
        return cellular_id;
    }

    public void setCellular_id(Object cellular_id) {
        this.cellular_id = cellular_id;
    }

    public List<Object> getWifi_aps() {
        return wifi_aps;
    }

    public void setWifi_aps(List<Object> wifi_aps) {
        this.wifi_aps = wifi_aps;
    }
}
