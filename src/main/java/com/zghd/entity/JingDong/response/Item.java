package com.zghd.entity.JingDong.response;

import java.util.List;

public class Item {

    //标题
    private String title;
    //描述
    private String desc;
    //广告来源
    private String ad_resource;
    //序号
    private String id;
    //1跳转类  2下载类
    private String media_style;
    //下载类时的下载链接
    private String download_url;
    //h5页面地址
    private String click_url;
    //deeplink
    private String dpl_url;
    //图
    private String img;
    //多图
    private List<String> imgs;
    //视频素材url
    private String video;
    //曝光
    private List<String> exposal_urls;
    //点击
    private List<String> click_monitor_urls;
    //视频-开始播放
    private String video_start_url;
    //视频-播放一半
    private String video_valid_url;
    //视频-播放结束
    private String video_finish_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAd_resource() {
        return ad_resource;
    }

    public void setAd_resource(String ad_resource) {
        this.ad_resource = ad_resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedia_style() {
        return media_style;
    }

    public void setMedia_style(String media_style) {
        this.media_style = media_style;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public String getDpl_url() {
        return dpl_url;
    }

    public void setDpl_url(String dpl_url) {
        this.dpl_url = dpl_url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<String> getExposal_urls() {
        return exposal_urls;
    }

    public void setExposal_urls(List<String> exposal_urls) {
        this.exposal_urls = exposal_urls;
    }

    public List<String> getClick_monitor_urls() {
        return click_monitor_urls;
    }

    public void setClick_monitor_urls(List<String> click_monitor_urls) {
        this.click_monitor_urls = click_monitor_urls;
    }

    public String getVideo_start_url() {
        return video_start_url;
    }

    public void setVideo_start_url(String video_start_url) {
        this.video_start_url = video_start_url;
    }

    public String getVideo_valid_url() {
        return video_valid_url;
    }

    public void setVideo_valid_url(String video_valid_url) {
        this.video_valid_url = video_valid_url;
    }

    public String getVideo_finish_url() {
        return video_finish_url;
    }

    public void setVideo_finish_url(String video_finish_url) {
        this.video_finish_url = video_finish_url;
    }
}
