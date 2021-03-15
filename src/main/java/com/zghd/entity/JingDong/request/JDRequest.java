package com.zghd.entity.JingDong.request;

import java.util.List;

/**
 * 京东请求参数组
 */
public class JDRequest {

    //唯一请求id
    private String id;
    //3.3
    private String version;
    //广告位信息
    private List<Imp> imp;
    //APP
    private App app;
    //设备信息
    private Device device;
    //用户信息
    private User user;
    //周围场景内容
    private List<Content> content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
