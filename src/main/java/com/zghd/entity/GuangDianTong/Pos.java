package com.zghd.entity.GuangDianTong;

/**
 * 广告位信息
 */
public class Pos {

    /**
     * 广告位id
     */
    private long id;
    /**
     * 广告位宽
     */
    private int width;
    /**
     * 广告位高
     */
    private int height;
    /**
     * 广告位请求数量
     */
    private int ad_count;
    /**
     * 最近曝光的广告位id
     */
    private String last_ad_ids;
    /**
     * 资质通荐频道id
     */
    private int channel;
    /**
     * 资质通荐页码数
     */
    private int page_number;
    /**
     * deeplink
     */
    private int deep_link_version;
    /**
     * 视频允许最大时长
     */
    private int max_duration;

    public String getLast_ad_ids() {
        return last_ad_ids;
    }

    public void setLast_ad_ids(String last_ad_ids) {
        this.last_ad_ids = last_ad_ids;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public int getDeep_link_version() {
        return deep_link_version;
    }

    public void setDeep_link_version(int deep_link_version) {
        this.deep_link_version = deep_link_version;
    }

    public int getMax_duration() {
        return max_duration;
    }

    public void setMax_duration(int max_duration) {
        this.max_duration = max_duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAd_count() {
        return ad_count;
    }

    public void setAd_count(int ad_count) {
        this.ad_count = ad_count;
    }
}
