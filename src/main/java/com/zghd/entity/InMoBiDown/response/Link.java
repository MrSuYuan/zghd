package com.zghd.entity.InMoBiDown.response;

import java.util.List;

public class Link {

    private List<String> clicktrackers;
    private String url;
    private String fallback;

    public List<String> getClicktrackers() {
        return clicktrackers;
    }

    public void setClicktrackers(List<String> clicktrackers) {
        this.clicktrackers = clicktrackers;
    }

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
}
