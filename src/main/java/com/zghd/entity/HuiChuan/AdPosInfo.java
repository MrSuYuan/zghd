package com.zghd.entity.HuiChuan;

import java.util.List;

/**
 * 广告位信息
 */
public class AdPosInfo {
    //+++广告位类型 枚举值，包括: 0 common id相关，必须有id；1 query 可以没有id，必须有query字段。对于common请求，我们会根据用户的兴趣，行为和页面信息，定向投放广告
    private String slot_type;
    //+++广告位id
    private String slot_id;
    //支持的广告样式集合数组，强烈建议填充，表示该版本客户端支持的样式集合，如果不填，默认使用广告平台当前设置的样式集合代替
    private List<String> ad_style;
    //+++数量，数量不做限制
    private String req_cnt;
    //自媒体ID
    private String wid;
    //+++广告位宽度（单位：像素）
    private String aw;
    //+++广告位高度（单位：像素）
    private String ah;
    private String bid;
    //
    private String query;
    private String ch;
    private List<AdPosExtInfo> ad_pos_ext_info;

    public String getSlot_type() {
        return slot_type;
    }

    public void setSlot_type(String slot_type) {
        this.slot_type = slot_type;
    }

    public String getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(String slot_id) {
        this.slot_id = slot_id;
    }

    public List<String> getAd_style() {
        return ad_style;
    }

    public void setAd_style(List<String> ad_style) {
        this.ad_style = ad_style;
    }

    public String getReq_cnt() {
        return req_cnt;
    }

    public void setReq_cnt(String req_cnt) {
        this.req_cnt = req_cnt;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getAw() {
        return aw;
    }

    public void setAw(String aw) {
        this.aw = aw;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public List<AdPosExtInfo> getAd_pos_ext_info() {
        return ad_pos_ext_info;
    }

    public void setAd_pos_ext_info(List<AdPosExtInfo> ad_pos_ext_info) {
        this.ad_pos_ext_info = ad_pos_ext_info;
    }
}
