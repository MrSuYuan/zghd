package com.zghd.entity.InMoBiDown.request;

import java.util.List;

/**
 * inmobi作为下游请求参数
 */
public class BidRequest {

    private String id;
    private List<ReqImp> imp;
    private ReqDevice device;
    private ReqApp app;
    private ReqUser user;
    private int at;
    private List<String> cur;
    private int tmax;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ReqImp> getImp() {
        return imp;
    }

    public void setImp(List<ReqImp> imp) {
        this.imp = imp;
    }

    public ReqDevice getDevice() {
        return device;
    }

    public void setDevice(ReqDevice device) {
        this.device = device;
    }

    public ReqApp getApp() {
        return app;
    }

    public void setApp(ReqApp app) {
        this.app = app;
    }

    public ReqUser getUser() {
        return user;
    }

    public void setUser(ReqUser user) {
        this.user = user;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public List<String> getCur() {
        return cur;
    }

    public void setCur(List<String> cur) {
        this.cur = cur;
    }

    public int getTmax() {
        return tmax;
    }

    public void setTmax(int tmax) {
        this.tmax = tmax;
    }
}
