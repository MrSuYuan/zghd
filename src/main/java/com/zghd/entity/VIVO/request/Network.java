package com.zghd.entity.VIVO.request;

/**
 * 网络信息
 */
public class Network {

    /**
     * 联网方式
     * 0未知 1未知 2345/g 100wifi
     */
    private int connectType;
    /**
     * 运营商
     * 0未知 1移动 2联通 3电信
     */
    private int carrier;
    /**
     * 运营商识别码
     */
    private int mccmnc;

    public int getConnectType() {
        return connectType;
    }

    public void setConnectType(int connectType) {
        this.connectType = connectType;
    }

    public int getCarrier() {
        return carrier;
    }

    public void setCarrier(int carrier) {
        this.carrier = carrier;
    }

    public int getMccmnc() {
        return mccmnc;
    }

    public void setMccmnc(int mccmnc) {
        this.mccmnc = mccmnc;
    }
}
