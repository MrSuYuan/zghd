package com.zghd.entity.WangMai;

/**
 * 网络信息
 */
public class Network {

    /**
     * Ipv4地址
     */
    private String ipv4;
    /**
     * 用于判断网速。
     * CONNECTION_UNKNOWN = 0 无法探测当前网络状态;
     * CELL_UNKNOWN = 1 蜂窝数据接入; 未知网络类型
     * CELL_2G = 2 蜂窝数据2G网络;
     * CELL_3G = 3 蜂窝数据3G网络;
     * CELL_4G = 4 蜂窝数据4G网;
     * CELL_5G =5 蜂窝数据5G网络;
     * WIFI = 100 Wi-Fi网络接入;
     * ETHERNET = 101 以太网接入;
     * NEW_TYPE = 999 未知新类型
     */
    private int connection_type;
    /**
     * 用于运营商定向广告
     * UNKNOWN_OPERATOR = 0 未知的运营商,
     * CHINA_MOBILE = 1 中国移动,
     * CHINA_TELECOM = 2 中国电信,
     * CHINA_UNICOM = 3 中国联通,
     * OTHER_OPERATOR = 99 其他运营商
     */
    private int operator_type;
    private String imsi;

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public int getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(int connection_type) {
        this.connection_type = connection_type;
    }

    public int getOperator_type() {
        return operator_type;
    }

    public void setOperator_type(int operator_type) {
        this.operator_type = operator_type;
    }
}
