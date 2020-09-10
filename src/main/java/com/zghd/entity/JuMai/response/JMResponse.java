package com.zghd.entity.JuMai.response;

import java.util.List;

/**
 * 俱脉返回参数
 */
public class JMResponse {

    //True/false,成功/失败
    private boolean status;
    //错误消息
    private String message;
    //广告信息
    private List<JMAd> ads;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JMAd> getAds() {
        return ads;
    }

    public void setAds(List<JMAd> ads) {
        this.ads = ads;
    }
}
