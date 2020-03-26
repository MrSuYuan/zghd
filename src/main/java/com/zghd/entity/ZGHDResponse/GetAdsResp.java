package com.zghd.entity.ZGHDResponse;

import java.util.List;

/**
 * 返回参数
 */
public class GetAdsResp {

    /**
     * 对应请求id
     */
    private String requestId;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 广告对象
     */
    private List<Ad> ads;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
