package com.zghd.entity.JuKanDian.request;

/**
 * 用户初始化
 */
public class User {

    private String appId;
    private String slotId;
    //渠道唯一标识
    private String appkey;
    //APP对应包名
    private String appid;
    //APP对应用户标识
    private String userid;
    //单次有效阅读奖励(毫)
    private int reward;
    //APP对应奖励的单位名称
    private String unit;
    //系统：Android/iOS
    private String os;
    //系统版本
    private String osversion;
    //手机品牌
    private String mobileboard;
    //手机型号
    private String mobilemodel;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getMobileboard() {
        return mobileboard;
    }

    public void setMobileboard(String mobileboard) {
        this.mobileboard = mobileboard;
    }

    public String getMobilemodel() {
        return mobilemodel;
    }

    public void setMobilemodel(String mobilemodel) {
        this.mobilemodel = mobilemodel;
    }

    @Override
    public String toString() {
        return "User{" +
                "appkey='" + appkey + '\'' +
                ", appid='" + appid + '\'' +
                ", userid='" + userid + '\'' +
                ", reward=" + reward +
                ", unit='" + unit + '\'' +
                ", os='" + os + '\'' +
                ", osversion='" + osversion + '\'' +
                ", mobileboard='" + mobileboard + '\'' +
                ", mobilemodel='" + mobilemodel + '\'' +
                '}';
    }
}
