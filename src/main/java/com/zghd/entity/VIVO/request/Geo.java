package com.zghd.entity.VIVO.request;

/**
 * 地理位置信息
 */
public class Geo {

    /**
     * 经度
     */
    private double lat;
    /**
     * 纬度
     */
    private double lng;
    /**
     * 时间
     */
    private long coordTime;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getCoordTime() {
        return coordTime;
    }

    public void setCoordTime(long coordTime) {
        this.coordTime = coordTime;
    }
}
