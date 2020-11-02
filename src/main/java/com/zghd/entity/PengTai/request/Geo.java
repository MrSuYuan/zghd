package com.zghd.entity.PengTai.request;

public class Geo {
    private float lat;
    private float lon;
    private int type;
    private int accuracy;
    private int lastfix;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getLastfix() {
        return lastfix;
    }

    public void setLastfix(int lastfix) {
        this.lastfix = lastfix;
    }
}
