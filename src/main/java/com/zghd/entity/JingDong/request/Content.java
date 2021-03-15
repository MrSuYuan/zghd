package com.zghd.entity.JingDong.request;

import java.util.List;

public class Content {

    //广告位所在的屏幕标题
    private String title;
    //广告位所在的场景标签.类别
    private List<String> cat;
    //广告位所在的场景关键字
    private String keywords;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCat() {
        return cat;
    }

    public void setCat(List<String> cat) {
        this.cat = cat;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
