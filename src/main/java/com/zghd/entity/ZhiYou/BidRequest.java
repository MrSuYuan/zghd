package com.zghd.entity.ZhiYou;

import java.util.List;

public class BidRequest {

    //竞价编号
    private String id;
    //版本协议 1.1.6
    private String version;
    //竞价请求售卖位置描述数组
    private List<Imp> imp;
    //站点信息
    private Site site;
    //app信息
    private App app;
    //设备信息
    private Device device;
    //用户信息
    private User user;
    //GFP(第一价格) GSP(第二价格)
    private int at;
    //流量类型 0测试 1正式
    private int test;
    //最大超时时间(毫秒)
    private int tmax;
    //扩展描述
    private Ext ext;

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

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getTmax() {
        return tmax;
    }

    public void setTmax(int tmax) {
        this.tmax = tmax;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }
}
