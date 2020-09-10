package com.zghd.entity.JuMai.request;

/**
 * 俱脉app信息
 */
public class App {

    //App 版本号
    private String appver;
    //App 名称
    private String appname;
    //App 包名
    private String pkgname;
    //广告位宽度
    private int w;
    //广告位高度
    private int h;

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
