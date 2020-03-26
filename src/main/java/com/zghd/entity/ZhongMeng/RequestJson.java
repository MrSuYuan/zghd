package com.zghd.entity.ZhongMeng;

/**
 * 众盟请求数据
 */
public class RequestJson {

    /**
     * 广告位请求配置数据
     */
    private ReqInfo reqInfo;
    /**
     * 广告位基本信息
     */
    private AdSlotInfo adSlotInfo;
    /**
     * 手机的相关配置
     */
    private MobileInfo mobileInfo;
    /**
     * 当前⽹网络连接信息
     */
    private NetWorkInfo networkInfo;
    /**
     * 定位数据(建议设置)
     */
    private CoordinateInfo coordinateInfo;

    public ReqInfo getReqInfo() {
        return reqInfo;
    }

    public void setReqInfo(ReqInfo reqInfo) {
        this.reqInfo = reqInfo;
    }

    public AdSlotInfo getAdSlotInfo() {
        return adSlotInfo;
    }

    public void setAdSlotInfo(AdSlotInfo adSlotInfo) {
        this.adSlotInfo = adSlotInfo;
    }

    public MobileInfo getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(MobileInfo mobileInfo) {
        this.mobileInfo = mobileInfo;
    }

    public NetWorkInfo getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(NetWorkInfo networkInfo) {
        this.networkInfo = networkInfo;
    }

    public CoordinateInfo getCoordinateInfo() {
        return coordinateInfo;
    }

    public void setCoordinateInfo(CoordinateInfo coordinateInfo) {
        this.coordinateInfo = coordinateInfo;
    }
}
