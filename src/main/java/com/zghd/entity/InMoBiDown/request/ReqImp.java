package com.zghd.entity.InMoBiDown.request;

public class ReqImp {

    private String id;
    private ReqNative aNative;
    private ReqBanner banner;
    private ReqPmp pmp;
    private ReqExt ext;
    private double bidfloor;
    private String bidfloorcurrency;
    private int instl;
    private int secure;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReqNative getaNative() {
        return aNative;
    }

    public void setaNative(ReqNative aNative) {
        this.aNative = aNative;
    }

    public ReqBanner getBanner() {
        return banner;
    }

    public void setBanner(ReqBanner banner) {
        this.banner = banner;
    }

    public ReqPmp getPmp() {
        return pmp;
    }

    public void setPmp(ReqPmp pmp) {
        this.pmp = pmp;
    }

    public ReqExt getExt() {
        return ext;
    }

    public void setExt(ReqExt ext) {
        this.ext = ext;
    }

    public double getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(double bidfloor) {
        this.bidfloor = bidfloor;
    }

    public String getBidfloorcurrency() {
        return bidfloorcurrency;
    }

    public void setBidfloorcurrency(String bidfloorcurrency) {
        this.bidfloorcurrency = bidfloorcurrency;
    }

    public int getInstl() {
        return instl;
    }

    public void setInstl(int instl) {
        this.instl = instl;
    }

    public int getSecure() {
        return secure;
    }

    public void setSecure(int secure) {
        this.secure = secure;
    }
}
