package com.zghd.entity.PengTai.response;

import java.util.List;

public class Link {

    private String url;
    private String fallback;
    private List<String> clicktrackers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    public List<String> getClicktrackers() {
        return clicktrackers;
    }

    public void setClicktrackers(List<String> clicktrackers) {
        this.clicktrackers = clicktrackers;
    }
}
