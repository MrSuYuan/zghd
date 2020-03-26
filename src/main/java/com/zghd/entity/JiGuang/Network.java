package com.zghd.entity.JiGuang;

import java.util.List;

/**
 * 网络信息
 */
public class Network {

    /**
     * 公网IPv4地址
     * @request true
     */
    private String ipv4;
    /**
     * 网络类型
     * UNACCESSIBLE 无权获取到网络状态
     * UNKNOWN 无法探测当前网络状态
     * CELL_UNKNOWN 蜂窝数据接入，未知网络类型
     * CELL_2G 蜂窝数据2G网络
     * CELL_3G 蜂窝数据3G网络
     * CELL_4G 蜂窝数据4G网络
     * CELL_5G 蜂窝数据5G网络
     * WIFI Wi-Fi网络接入
     * ETHERNET 以太网接入
     * NEW_TYPE 未知新类型
     * @request true
     */
    private String connection_type;
    /**
     * 运营商
     * UNACCESSIBLE 无权获取到运营商类型
     * UNKNOWN 未知的运营商
     * CHINA_MOBILE 中国移动
     * CHINA_TELECOM 中国电信
     * CHINA_UNICOM 中国联通
     * OTHER 其他运营商
     * @request true
     */
    private String operator;
    /**
     * 基站ID
     * @request true
     */
    private List<String> cell_id;

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<String> getCell_id() {
        return cell_id;
    }

    public void setCell_id(List<String> cell_id) {
        this.cell_id = cell_id;
    }
}
