package com.zghd.entity.YouKu.request;

import java.util.List;

/**
 * 优酷请求参数示例
 */
public class YKRequest {

    //请求ID
    private String id;
    //曝光对象，一次request可以包含多个imp
    private List<Imp> imp;
    //媒体站点对象。注意：同一个请求中site和app只会有一个出现，当请求由浏览器发起时是site; 当请求由移动app发起时有app。
    private Site site;
    //移动app对象。注意：同一个请求中site和app只有一个出现。
    private App app;
    //设备对象
    private Device device;
    //用户对象
    private User user;

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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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
}
