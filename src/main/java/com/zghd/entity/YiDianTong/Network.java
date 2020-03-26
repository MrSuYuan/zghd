package com.zghd.entity.YiDianTong;

/**
 * 网络信息
 */
public class Network {

    /**
     * IPv4 地址
     * @request true
     */
    private String ip;
    /**
     * 网络类型
     * 0--CONNECTION_UNKNOWN
     * 1--CELL_UNKNOWN
     * 2--CELL_2G
     * 3--CELL_3G
     * 4--CELL_4G
     * 5--CELL_5G
     * 100—WIFI
     * 101—ETHERNET
     * 999--NEW_TYPE
     * @request true
     */
    private int connectionType;
    /**
     * 运营商类型
     * 0--UNKNOWN_OPERATOR
     * 1--CHINA_MOBILE
     * 2--CHINA_TELECOM
     * 3--CHINA_UNICOM
     * 99--OTHER_OPERATOR
     * @request true
     */
    private int operatorType;
    /**
     * 基站ID
     * @request false
     */
    private String cellular_id;
    /**
     * 纬度(无法获取填0)
     * @request true
     */
    private float lat;
    /**
     * 经度(无法获取填0)
     * @request true
     */
    private float lon;
    /**
     * 移动国家码
     * @request false
     */
    private String mcc;
    /**
     * 移动网络码mnc
     * @request false
     */
    private String mnc;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public String getCellular_id() {
        return cellular_id;
    }

    public void setCellular_id(String cellular_id) {
        this.cellular_id = cellular_id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }
}
