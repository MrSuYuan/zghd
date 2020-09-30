package com.zghd.entity.ZhiYou;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

//广告位信息
public class Imp {
    //广告位曝光位置,从1开始 2 3 4
    private String id;
    //宽度
    private int aw;
    //高度
    private int ah;
    //广告位id
    private String tagid;
    //竞价信息,默认0
    private Float bidfloor;
    //视频广告
    private Video video;
    //图片广告
    private Banner banner;
    //原生广告
    @JSONField(name = "native")
    private Native aNative;
    //私有交易，当流量售卖位为私有交易模式即： PB (保价模式 )，PDB(保价保量模式 ) 时，该字段 为必填
    private Pmp pmp;
    //物料类型 MVB:视频物料， MGT:图文物料， MGB:仅图物料 MTB:仅文物料
    private List<String> mst;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAw() {
        return aw;
    }

    public void setAw(int aw) {
        this.aw = aw;
    }

    public int getAh() {
        return ah;
    }

    public void setAh(int ah) {
        this.ah = ah;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public Float getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(Float bidfloor) {
        this.bidfloor = bidfloor;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Pmp getPmp() {
        return pmp;
    }

    public void setPmp(Pmp pmp) {
        this.pmp = pmp;
    }

    public List<String> getMst() {
        return mst;
    }

    public void setMst(List<String> mst) {
        this.mst = mst;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Native getaNative() {
        return aNative;
    }

    public void setaNative(Native aNative) {
        this.aNative = aNative;
    }
}
