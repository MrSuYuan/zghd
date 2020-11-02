package com.zghd.entity.PengTai.response;

import java.util.List;

public class Adm {

    private String ver;
    private List<String> assets;
    private Link link;
    private List<String> imptrackers;
    private Ext ext;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public List<String> getAssets() {
        return assets;
    }

    public void setAssets(List<String> assets) {
        this.assets = assets;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public List<String> getImptrackers() {
        return imptrackers;
    }

    public void setImptrackers(List<String> imptrackers) {
        this.imptrackers = imptrackers;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }
}
