package com.zghd.entity.RuiXi;

public class RXData {

    private String sign;
    private String token;
    private RXRequest request;

    public RXRequest getRequest() {
        return request;
    }

    public void setRequest(RXRequest request) {
        this.request = request;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
