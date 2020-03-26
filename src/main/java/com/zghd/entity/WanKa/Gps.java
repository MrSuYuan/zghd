package com.zghd.entity.WanKa;

/**
 * 定位信息
 */
public class Gps {

    /**
     * 坐标类型 1：全球卫星定位系统坐标系，2：国家测绘局坐标系，3：百度坐标系，4：其他坐标系
     * @required false
     */
    private int coordinate_type;
    /**
     * 径度
     * @required false
     */
    private double lon;
    /**
     * 纬度
     * @required false
     */
    private double lat;
    /**
     * 经纬度精度半径，单位为米。 该参数会用于基于地理位置的广告的定向，正确填写有助于提高流量变现效果
     * @required false
     */
    private double location_accuracy;
    /**
     * 获取经纬度(lat/lng)的时间。其值为从 GMT 1970-01-01 00:00:00 至今的毫秒值
     * @required true
     */
    private Long coord_time;

    public int getCoordinate_type() {
        return coordinate_type;
    }

    public void setCoordinate_type(int coordinate_type) {
        this.coordinate_type = coordinate_type;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLocation_accuracy() {
        return location_accuracy;
    }

    public void setLocation_accuracy(double location_accuracy) {
        this.location_accuracy = location_accuracy;
    }

    public Long getCoord_time() {
        return coord_time;
    }

    public void setCoord_time(Long coord_time) {
        this.coord_time = coord_time;
    }
}
