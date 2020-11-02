package com.zghd.entity.PengTai.request;

public class DeviceExt {
    private String imei;
    private String oaid;
    private String androidid;
    private String db;
    private String mcc;
    private String mnc;
    private String csc;
    private String sdkVerAndroid;
    private String abiType;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getCsc() {
        return csc;
    }

    public void setCsc(String csc) {
        this.csc = csc;
    }

    public String getSdkVerAndroid() {
        return sdkVerAndroid;
    }

    public void setSdkVerAndroid(String sdkVerAndroid) {
        this.sdkVerAndroid = sdkVerAndroid;
    }

    public String getAbiType() {
        return abiType;
    }

    public void setAbiType(String abiType) {
        this.abiType = abiType;
    }
}
