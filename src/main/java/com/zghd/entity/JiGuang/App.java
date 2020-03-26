package com.zghd.entity.JiGuang;

/**
 * app参数组
 */
public class App {

    /**
     * 应用版本
     * @request true
     */
    private String version;
    /**
     * 应用包名
     * @request true
     */
    private String bundle;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }
}
