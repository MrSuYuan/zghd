package com.zghd.entity.YouKu.request;

/**
 * 曝光对象，一次request可以包含多个imp
 */
public class Imp {

    //曝光ID，在同一次请求中，区分不同的广告位
    private String id;
    //广告位ID
    private String tagid;
    //底价,单位是分/千次曝光,即CPM
    private float bidfloor;
    //banner类型的广告位
    private Banner banner;
    //video类型的广告位
    private Video video;
    //native类型的广告位
    private Native aNative;
    //Preferred Deal相关参数，参看pmp字段说明
    private Pmp pmp;
    //扩展字段，参看imp.ext字段的说明
    private Ext ext;
    //secure=1 https url, secure=0 http url,默认0
    private int secure;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public float getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(float bidfloor) {
        this.bidfloor = bidfloor;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Native getaNative() {
        return aNative;
    }

    public void setaNative(Native aNative) {
        this.aNative = aNative;
    }

    public Pmp getPmp() {
        return pmp;
    }

    public void setPmp(Pmp pmp) {
        this.pmp = pmp;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }

    public int getSecure() {
        return secure;
    }

    public void setSecure(int secure) {
        this.secure = secure;
    }
}
