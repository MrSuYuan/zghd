package com.zghd.entity.WangMai;

/**
 * 广告信息
 */
public class Adslot {

    /**
     * 代码位id在ssp平台获取
     */
    private String adslot_id;
    /**
     * 当前设备可展现广告区域的尺寸
     */
    private AdslotSize adslot_size;
    /**
     * 是否支持deeplink，0=不支持，1=支持，默认为0
     */
    private int support_deeplink;
    /**
     * 是否需要返回https，0=不需要，1=需要，默认为0
     */
    private int secure;

    public String getAdslot_id() {
        return adslot_id;
    }

    public void setAdslot_id(String adslot_id) {
        this.adslot_id = adslot_id;
    }

    public AdslotSize getAdslot_size() {
        return adslot_size;
    }

    public void setAdslot_size(AdslotSize adslot_size) {
        this.adslot_size = adslot_size;
    }

    public int getSupport_deeplink() {
        return support_deeplink;
    }

    public void setSupport_deeplink(int support_deeplink) {
        this.support_deeplink = support_deeplink;
    }

    public int getSecure() {
        return secure;
    }

    public void setSecure(int secure) {
        this.secure = secure;
    }
}
