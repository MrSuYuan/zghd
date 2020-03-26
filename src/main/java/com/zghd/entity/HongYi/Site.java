package com.zghd.entity.HongYi;

/**
 * 站点信息(非必填)
 */
public class Site {

    /**
     * 站点主域
     */
    private String domain;
    /**
     * 当前页面
     */
    private String urls;
    /**
     * 当前页面的标题
     */
    private String title;
    /**
     * 当前页面的metadata
     */
    private String keywords;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
