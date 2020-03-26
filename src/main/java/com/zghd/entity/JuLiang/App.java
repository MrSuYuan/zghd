package com.zghd.entity.JuLiang;

/**
 * 应用信息
 */
public class App {

    /**
     * 应用包名
     */
    private String pkg;
    /**
     * 应用名称
     */
    private String an;
    /**
     * 应用版本(例如：1.0.0)
     */
    private String ver;
    /**
     * 版本号(例如12)
     */
    private int vc;
    /**
     * 0/1:是否支持广点通类下载
     */
    private int isgdt;

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public int getVc() {
        return vc;
    }

    public void setVc(int vc) {
        this.vc = vc;
    }

    public int getIsgdt() {
        return isgdt;
    }

    public void setIsgdt(int isgdt) {
        this.isgdt = isgdt;
    }
}
