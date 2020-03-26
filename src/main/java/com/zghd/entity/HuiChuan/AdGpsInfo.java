package com.zghd.entity.HuiChuan;

/**
 * GPS信息
 */
public class AdGpsInfo {
    //+++时间
    private String gps_time;
    //+++经度
    private String lng;
    //+++纬度
    private String lat;
    private String amap_code;

    public String getGps_time() {
        return gps_time;
    }

    public void setGps_time(String gps_time) {
        this.gps_time = gps_time;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAmap_code() {
        return amap_code;
    }

    public void setAmap_code(String amap_code) {
        this.amap_code = amap_code;
    }
}
