package com.zghd.entity.ZhongTi.response;

import java.util.List;

public class ZTResponse {

    private String request_id;
    private int error_code;
    private int expiration_time;
    private List<ZTAds> ads;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(int expiration_time) {
        this.expiration_time = expiration_time;
    }

    public List<ZTAds> getAds() {
        return ads;
    }

    public void setAds(List<ZTAds> ads) {
        this.ads = ads;
    }
}
