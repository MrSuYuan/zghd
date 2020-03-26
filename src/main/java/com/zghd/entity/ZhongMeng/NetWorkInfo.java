package com.zghd.entity.ZhongMeng;

/**
 * 当前⽹网络连接信息
 */
public class NetWorkInfo {

    /**
     * 系统webview的user-agent
     */
    private String ua;
    /**
     * IP地址
     */
    private String ip;
    /**
     * ip地址类型 0:ipv4 1:ipv6 默认:0
     */
    private int ipType;
    /**
     * 是否是Https环境 0:不不是 1:是 默认:0
     */
    private int httpType;

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

    public int getIpType() {
        return ipType;
    }

    public void setIpType(int ipType) {
        this.ipType = ipType;
    }

    public int getHttpType() {
        return httpType;
    }

    public void setHttpType(int httpType) {
        this.httpType = httpType;
    }
}
