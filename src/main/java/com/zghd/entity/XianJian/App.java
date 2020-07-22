package com.zghd.entity.XianJian;

import java.util.List;

public class App {

    private String id;
    private String name;
    private String bundle;
    private String domain;
    private String ver;
    private boolean paid;
    private List<String> cat;
    private List<String> pagecat;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public List<String> getCat() {
        return cat;
    }

    public void setCat(List<String> cat) {
        this.cat = cat;
    }

    public List<String> getPagecat() {
        return pagecat;
    }

    public void setPagecat(List<String> pagecat) {
        this.pagecat = pagecat;
    }
}