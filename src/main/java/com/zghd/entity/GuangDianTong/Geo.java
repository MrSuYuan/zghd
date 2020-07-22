package com.zghd.entity.GuangDianTong;

/**
 * 地理位置信息
 */
public class Geo {

    /**
     * 纬度*1,000,000
     */
    private int lat;
    /**
     * 经度*1,000,000
     */
    private int lng;
    /**
     * 经纬度精度半径
     */
    private double location_accuracy;
    /**
     * 获取经纬度(lat/lng)的时间
     */
    private long coord_time;

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public double getLocation_accuracy() {
        return location_accuracy;
    }

    public void setLocation_accuracy(double location_accuracy) {
        this.location_accuracy = location_accuracy;
    }

    public long getCoord_time() {
        return coord_time;
    }

    public void setCoord_time(long coord_time) {
        this.coord_time = coord_time;
    }
}
