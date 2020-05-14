package com.zghd.entity.RuiXi;

public class RXRequest {

    private String appName;
    private String packageName;
    private String appVersion;
    private AdSlot adslot;
    private Device device;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public AdSlot getAdslot() {
        return adslot;
    }

    public void setAdslot(AdSlot adslot) {
        this.adslot = adslot;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
