package com.zghd.entity.XinSheng;

/**
 * 定位数据信息
 */
public class Coordinate {

    /**
     * 坐标类型
     * 0:全球卫星定位系统坐标
     * 1:国家测绘局坐标系
     * 2:百度坐标系
     */
    private int ctype;
    /**
     * 经度
     */
    private double lng;
    /**
     * 纬度
     */
    private double lat;
    /**
     * 获取坐标信息的时间戳,单位为毫秒
     */
    private long timestamp;


    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
