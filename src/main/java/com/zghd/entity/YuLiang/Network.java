package com.zghd.entity.YuLiang;

/**
 * 网络
 */
public class Network {

    /**
     *
     * @request true
     */
    private int connection_type;
    /**
     * 运营商
     * 46000中国移动  46001中国致通  46003中国电信  46020中国铁通
     * @request true
     */
    private String carrier;
    /**
     * ip地址
     * @request false
     */
    private String ip;

    public int getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(int connection_type) {
        this.connection_type = connection_type;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
