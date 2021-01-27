package com.zghd.entity.YouKu.response;

import java.util.List;

/**
 * 针对单次曝光的出价
 */
public class Bid {

    //DSP对该次出价分配的ID
    private String id;
    //Bid Request中对应的曝光ID
    private String impid;
    //参与竞价的原生广告创意信息
    private Native aNative;
    //DSP出价，单位是分/千次曝光，即CPM
    private float price;
    //win notice url（已废弃，返回不会报错，效果和曝光监测一致）
    private String nurl;
    //广告素材URL。banner，贴片，暂停，角标，开机图资源必填。注意：PDB2.0该字段无效，统一接收crid。
    private String adm;
    //DSP系统中的创意ID。信息流，焦点图资源必填。保证在DSP侧的ID唯一性，作为竞价时的唯一创意ID，否则会引起素材投放混乱。
    private String crid;
    //广告主ID，对应广告审核的广告主ID
    private String advertiser;
    //广告主行业ID，需要使用YOUKU系统的行业
    private String industry;
    //曝光监测URL，可以有多个。注意是json数组(@Deprecated，版本1.4以后会删除这个字段，请使用ext.pm 目前版本1.3两个都支持)
    private List<String> pvm;
    //点击目标URL，可以包括点击监测串。(@Deprecated，版本1.4以后会删除这个字段，请使用ext.ldp) 目前版本1.3两个都支持
    private String clickm;
    //DSP参加的deal id
    private String dealid;
    //DSP参加PDB的订单ID
    private String orderid;
    //扩展字段
    private Ext ext;

    public Native getaNative() {
        return aNative;
    }

    public void setaNative(Native aNative) {
        this.aNative = aNative;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImpid() {
        return impid;
    }

    public void setImpid(String impid) {
        this.impid = impid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNurl() {
        return nurl;
    }

    public void setNurl(String nurl) {
        this.nurl = nurl;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public List<String> getPvm() {
        return pvm;
    }

    public void setPvm(List<String> pvm) {
        this.pvm = pvm;
    }

    public String getClickm() {
        return clickm;
    }

    public void setClickm(String clickm) {
        this.clickm = clickm;
    }

    public String getDealid() {
        return dealid;
    }

    public void setDealid(String dealid) {
        this.dealid = dealid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }
}
