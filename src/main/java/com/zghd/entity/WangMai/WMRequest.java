package com.zghd.entity.WangMai;
/**
 * 旺脉请求
 */
public class WMRequest {

    /**
     * 签名
     */
    private String sign;
    /**
     * qpptoken
     */
    private String apptoken;
    /**
     * 参数组
     */
    private Data data;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
