package com.zghd.entity.HongYi;

/**
 * 网络信息
 */
public class Network {

    /**
     * ip
     */
    private String ip;
    /**
     * 网络类型
     * 1.wifi  2.未知  3.2g  4.3g  5.4g
     */
    private int type;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
