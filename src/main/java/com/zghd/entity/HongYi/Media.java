package com.zghd.entity.HongYi;

import java.util.List;

/**
 * 媒体来源信息
 */
public class Media {

    /**
     * 媒体类别 (1.app 2.h5)
     */
    private int type;
    /**
     * 当前 app 的信息 api必 须
     */
    private App app;
    /**
     * 站点信息
     */
    private Site site;
    /**
     * 浏览器信息
     */
    private Browser browser;
    /**
     * 支持video
     */
    private List<Integer> support_feature;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public List<Integer> getSupport_feature() {
        return support_feature;
    }

    public void setSupport_feature(List<Integer> support_feature) {
        this.support_feature = support_feature;
    }
}
