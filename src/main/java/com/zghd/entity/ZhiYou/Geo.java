package com.zghd.entity.ZhiYou;
//地址信息
public class Geo {
    //维度
    private Float lat;
    //经度
    private Float lon;
    //坐标系
    private int coordinate;
    //时间戳
    private Long timestamp;
    //定位精度
    private int accu;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getAccu() {
        return accu;
    }

    public void setAccu(int accu) {
        this.accu = accu;
    }
}
