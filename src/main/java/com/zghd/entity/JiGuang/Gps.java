package com.zghd.entity.JiGuang;

/**
 * GPS参数
 */
public class Gps {

    /**
     * GPS坐标系类型
     * @request true
     */
    private String coordinate_type;
    /**
     * GPS坐标经度
     * @request true
     */
    private Double longitude;
    /**
     * GPS坐标维度
     * @request true
     */
    private Double latitude;
    /**
     * GPS时间戳信息
     * @request true
     */
    private Long timestamp;

    public String getCoordinate_type() {
        return coordinate_type;
    }

    public void setCoordinate_type(String coordinate_type) {
        this.coordinate_type = coordinate_type;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
