package com.zghd.entity.ZGHDResponse;

import java.util.List;

/**
 * ad信息
 */
public class Ad {

    /**
     * 广告位id
     */
    private String slotId;
    /**
     * 广告唯一标示
     */
    private String adKey;
    /**
     * HTML片段
     */
    private String htmlSnippet;
    /**
     * "广告"小图标地址
     */
    private String adtext;
    /**
     * "熊掌"图标地址
     */
    private String adlogo;
    /**
     * 物料元数据组
     */
    private List<MaterialMeta> metaGroup;
    /**
     * 视频播放进度监控信息
     */
    private List<Track> tracks;
    //价格
    private double p;

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getAdKey() {
        return adKey;
    }

    public void setAdKey(String adKey) {
        this.adKey = adKey;
    }

    public String getHtmlSnippet() {
        return htmlSnippet;
    }

    public void setHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
    }

    public String getAdtext() {
        return adtext;
    }

    public void setAdtext(String adtext) {
        this.adtext = adtext;
    }

    public String getAdlogo() {
        return adlogo;
    }

    public void setAdlogo(String adlogo) {
        this.adlogo = adlogo;
    }

    public List<MaterialMeta> getMetaGroup() {
        return metaGroup;
    }

    public void setMetaGroup(List<MaterialMeta> metaGroup) {
        this.metaGroup = metaGroup;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
