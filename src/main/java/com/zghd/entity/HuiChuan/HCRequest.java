package com.zghd.entity.HuiChuan;

import java.util.List;

/**
 * 汇川请求参数
 */
public class HCRequest {
    private AdDeviceInfo ad_device_info;
    private AdAppInfo ad_app_info;
    private AdGpsInfo ad_gps_info;
    private List<AdPosInfo> ad_pos_info;
    private AdPageInfo page_info;
    private AdResInfo res_info;
    private List<AdExtInfo> ext_info;

    public AdAppInfo getAd_app_info() {
        return ad_app_info;
    }

    public void setAd_app_info(AdAppInfo ad_app_info) {
        this.ad_app_info = ad_app_info;
    }

    public AdDeviceInfo getAd_device_info() {
        return ad_device_info;
    }

    public void setAd_device_info(AdDeviceInfo ad_device_info) {
        this.ad_device_info = ad_device_info;
    }

    public AdGpsInfo getAd_gps_info() {
        return ad_gps_info;
    }

    public void setAd_gps_info(AdGpsInfo ad_gps_info) {
        this.ad_gps_info = ad_gps_info;
    }


    public AdPageInfo getPage_info() {
        return page_info;
    }

    public void setPage_info(AdPageInfo page_info) {
        this.page_info = page_info;
    }

    public AdResInfo getRes_info() {
        return res_info;
    }

    public void setRes_info(AdResInfo res_info) {
        this.res_info = res_info;
    }

    public List<AdPosInfo> getAd_pos_info() {
        return ad_pos_info;
    }

    public void setAd_pos_info(List<AdPosInfo> ad_pos_info) {
        this.ad_pos_info = ad_pos_info;
    }

    public List<AdExtInfo> getExt_info() {
        return ext_info;
    }

    public void setExt_info(List<AdExtInfo> ext_info) {
        this.ext_info = ext_info;
    }
}
