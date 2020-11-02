package com.zghd.entity.ZhongTi.response;

import java.util.List;

public class ZTAds {

    private String adslot_id;
    private String html_snippet;
    private List<MetaGroup> meta_group;
    private List<AdTracking> ad_tracking;

    public String getAdslot_id() {
        return adslot_id;
    }

    public void setAdslot_id(String adslot_id) {
        this.adslot_id = adslot_id;
    }

    public String getHtml_snippet() {
        return html_snippet;
    }

    public void setHtml_snippet(String html_snippet) {
        this.html_snippet = html_snippet;
    }

    public List<MetaGroup> getMeta_group() {
        return meta_group;
    }

    public void setMeta_group(List<MetaGroup> meta_group) {
        this.meta_group = meta_group;
    }

    public List<AdTracking> getAd_tracking() {
        return ad_tracking;
    }

    public void setAd_tracking(List<AdTracking> ad_tracking) {
        this.ad_tracking = ad_tracking;
    }
}
