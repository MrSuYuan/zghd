package com.zghd.entity.YongQi;

/**
 * app信息
 */
public class App {

    /**
     * 应用id由媒体方生成必须唯一
     */
    private String id;
    /**
     * 应用名称
     */
    private String name;
    /**
     * 应用版本
     */
    private String ver;
    /**
     * 安卓包名 / ios的iTunesID
     */
    private String bundle;
    /**
     * 是否付费app  0免费  1付费  2内付费
     */
    private int paid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
