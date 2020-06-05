package com.zghd.entity.OPPO;

public class OPPORequest {

    //固定1
    private int apiVersion;
    //联盟API 版本号，作兼容使用(由OPPO 这边分配)  108
    private int apiVc;
    //商店版本号  5500
    private int appStoreVc;
    //应用信息
    private AppInfo appInfo;
    //广告位信息
    private PosInfo posInfo;
    //设备信息
    private DevInfo devInfo;

    public int getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getApiVc() {
        return apiVc;
    }

    public void setApiVc(int apiVc) {
        this.apiVc = apiVc;
    }

    public int getAppStoreVc() {
        return appStoreVc;
    }

    public void setAppStoreVc(int appStoreVc) {
        this.appStoreVc = appStoreVc;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public PosInfo getPosInfo() {
        return posInfo;
    }

    public void setPosInfo(PosInfo posInfo) {
        this.posInfo = posInfo;
    }

    public DevInfo getDevInfo() {
        return devInfo;
    }

    public void setDevInfo(DevInfo devInfo) {
        this.devInfo = devInfo;
    }
}
