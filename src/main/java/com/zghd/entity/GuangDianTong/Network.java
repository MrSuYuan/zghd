package com.zghd.entity.GuangDianTong;

/**
 * 网络信息
 */
public class Network {

    /**
     * 联网方式 0未知 2wifi 22G 33G 44G
     */
    private int connect_type;
    /**
     * 运营商 0未知 1移动 2联通 3电信
     */
    private int carrier;

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
}
