package com.zghd.entity.ZGHDRequest;

/**
 * 网络信息
 */
public class Network {

    /**
     * IPv4地址
     */
    private String ip;
    /**
     * 运营商类型
     * 0-	未知
     * 1-	中国移动
     * 2-	中国电信
     * 3-	中国联通
     * 99-其他运营商
     */
    private int operatorType;
    /**
     * 网络类型
     * 0-	未知网络
     * 2- 2G
     * 3- 3G
     * 4- 4G
     * 5- 5G
     * 100-WIFI
     * 101—ETHERNET
     * 999--NEW_TYPE
     */
    private int connectionType;
    /**
     * 纬度(无法获取填0)
     */
    private double lat;
    /**
     * 经度(无法获取填0)
     */
    private double lon;
    /**
     * 基站ID
     */
    private String cellular_id;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
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

    public String getCellular_id() {
        return cellular_id;
    }

    public void setCellular_id(String cellular_id) {
        this.cellular_id = cellular_id;
    }
}
