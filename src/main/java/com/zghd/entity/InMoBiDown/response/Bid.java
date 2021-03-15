package com.zghd.entity.InMoBiDown.response;

import java.util.List;

public class Bid {

    private String adid;
    private AdmObject admobject;
    private List<String> adomain;
    private int cid;
    private int crid;
    private String id;
    private int impid;
    private String iurl;
    private String nurl;
    private String burl;
    private double price;

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public AdmObject getAdmobject() {
        return admobject;
    }

    public void setAdmobject(AdmObject admobject) {
        this.admobject = admobject;
    }

    public List<String> getAdomain() {
        return adomain;
    }

    public void setAdomain(List<String> adomain) {
        this.adomain = adomain;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCrid() {
        return crid;
    }

    public void setCrid(int crid) {
        this.crid = crid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImpid() {
        return impid;
    }

    public void setImpid(int impid) {
        this.impid = impid;
    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl;
    }

    public String getNurl() {
        return nurl;
    }

    public void setNurl(String nurl) {
        this.nurl = nurl;
    }

    public String getBurl() {
        return burl;
    }

    public void setBurl(String burl) {
        this.burl = burl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
