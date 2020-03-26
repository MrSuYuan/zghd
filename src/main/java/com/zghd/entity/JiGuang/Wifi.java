package com.zghd.entity.JiGuang;

/**
 * WIFI参数
 */
public class Wifi {

    /**
     * WIFI mac地址
     * @request true
     */
    private String mac;
    /**
     * WIFI 信号强度
     * @request true
     */
    private int rssi;
    /**
     * WIFI 名称
     * @request true
     */
    private String name;
    /**
     * 当前是否连接此WIFI
     * @request true
     */
    private Boolean is_connected;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_connected() {
        return is_connected;
    }

    public void setIs_connected(Boolean is_connected) {
        this.is_connected = is_connected;
    }
}
