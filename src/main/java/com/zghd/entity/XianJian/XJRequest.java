package com.zghd.entity.XianJian;

import java.util.List;

/**
 * 先荐广告平台
 */
public class XJRequest {
    private String id;
    private List<Imp> imp;
    private App app;
    private Device device;

    /*private Site site;
    private User user;
    private List<String> bcat;
    private List<String> badv;
    private List<String> bkw;
    private boolean test;
    private int tmax;
    private List<String> cur;
    private Ext ext;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Imp> getImp() {
        return imp;
    }

    public void setImp(List<Imp> imp) {
        this.imp = imp;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
