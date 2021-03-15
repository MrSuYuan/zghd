package com.zghd.entity.JingDong.response;

public class Bid {

    //京东生成的id
    private String id;
    //广告id
    private String adid;
    //素材对象
    private Adm adm;
    //对应请求中的imp的id
    private String impid;
    //竞价价格
    private double price;
    //告知京东赢得 bid，并通过宏替换%%WIN_PRICE%%提供二价 。非竞价无返回
    private String nurl;
    //广告类型 1非商品  3商品
    private String ad_type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public String getImpid() {
        return impid;
    }

    public void setImpid(String impid) {
        this.impid = impid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNurl() {
        return nurl;
    }

    public void setNurl(String nurl) {
        this.nurl = nurl;
    }

    public Adm getAdm() {
        return adm;
    }

    public void setAdm(Adm adm) {
        this.adm = adm;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }
}
