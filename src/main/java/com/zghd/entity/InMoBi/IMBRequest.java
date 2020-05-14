package com.zghd.entity.InMoBi;

import java.util.Map;

public class IMBRequest {

    private App app;
    private Map<String,Object> imp;
    private Device device;
    private Ext ext;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Map<String, Object> getImp() {
        return imp;
    }

    public void setImp(Map<String, Object> imp) {
        this.imp = imp;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }
}
