package com.zghd.entity.PengTai.request;

import java.util.List;

public class PTRequest {

    private Device device;
    private User user;
    private List<Imp> imp;
    private String id;
    private List<String> cur;
    private App app;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCur() {
        return cur;
    }

    public void setCur(List<String> cur) {
        this.cur = cur;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Imp> getImp() {
        return imp;
    }

    public void setImp(List<Imp> imp) {
        this.imp = imp;
    }
}
