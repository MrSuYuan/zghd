package com.zghd.entity.XiaoZhi;

/**
 * 设备相关信息
 */
public class DeviceId {

    /**
     * 设备id值
     */
    private String device_id;
    /**
     * 设备id类型
     * 1  imei
     * 2  idfa
     * 3  android id
     * 4  mac
     * 5  idfv
     * 8  imsi
     * Android 1 3 4 8必传
     * iOS支持类型2,5 推荐传IFDA
     */
    private int device_id_type;
    /**
     * 设备id加密类型
     * 0  原始值
     * 1  MD5加密
     * 推荐原始
     */
    private int hash_type;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getDevice_id_type() {
        return device_id_type;
    }

    public void setDevice_id_type(int device_id_type) {
        this.device_id_type = device_id_type;
    }

    public int getHash_type() {
        return hash_type;
    }

    public void setHash_type(int hash_type) {
        this.hash_type = hash_type;
    }
}
