package com.zghd.entity.ZhiYou;

import java.util.List;

//app信息
public class App {
    //appid
    private String id;
    //名称
    private String name;
    //包名
    private String bundle;
    //版本号
    private String ver;
    //是否付费 1是 2否(默认)
    private int paid;
    //应用分类描述
    private String keywords;
    //媒体类型
    private List<String> cat;

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

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<String> getCat() {
        return cat;
    }

    public void setCat(List<String> cat) {
        this.cat = cat;
    }
}
