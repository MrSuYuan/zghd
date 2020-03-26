package com.zghd.entity.ZhongMeng;

/**
 * 定位数据(建议设置)
 */
public class CoordinateInfo {

    /**
     * 坐标类型
     * 0:全球卫星定位系统坐标
     * 1:国家测绘局坐标系
     * 2:百度坐标系
     * 3.高德坐标系
     * 4.腾讯坐标系
     * 5.谷歌坐标系
     * 100.其他
     */
    private int coordinateType;
    /**
     * 经度
     */
    private double lng;
    /**
     * 纬度
     */
    private double lat;
    /**
     * 获取坐标信息的时间戳,其值为从GMT
     * 1970-01-01 00:00:00⾄至今的毫秒值
     */
    private long timestamp;

    public int getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(int coordinateType) {
        this.coordinateType = coordinateType;
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
