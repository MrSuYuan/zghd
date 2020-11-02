package com.zghd.entity.ZhongTi.response;

import java.util.List;

public class MetaGroup {

    private int material_type;
    private String interaction_type;
    private String deep_link;
    private String clk_url;
    private String title;
    private String advertisement;
    private String media;
    private Size size;
    private String icon;
    private String app_package_name;
    private String video;
    private int video_duration;
    private int video_keepdur;
    private String video_ldpg_html;
    private List<String> images;
    private String video_click_mode;
    private String fallback_deep_link;
    private int click_position;

    public int getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(int material_type) {
        this.material_type = material_type;
    }

    public String getInteraction_type() {
        return interaction_type;
    }

    public void setInteraction_type(String interaction_type) {
        this.interaction_type = interaction_type;
    }

    public String getDeep_link() {
        return deep_link;
    }

    public void setDeep_link(String deep_link) {
        this.deep_link = deep_link;
    }

    public String getClk_url() {
        return clk_url;
    }

    public void setClk_url(String clk_url) {
        this.clk_url = clk_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getApp_package_name() {
        return app_package_name;
    }

    public void setApp_package_name(String app_package_name) {
        this.app_package_name = app_package_name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(int video_duration) {
        this.video_duration = video_duration;
    }

    public int getVideo_keepdur() {
        return video_keepdur;
    }

    public void setVideo_keepdur(int video_keepdur) {
        this.video_keepdur = video_keepdur;
    }

    public String getVideo_ldpg_html() {
        return video_ldpg_html;
    }

    public void setVideo_ldpg_html(String video_ldpg_html) {
        this.video_ldpg_html = video_ldpg_html;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getVideo_click_mode() {
        return video_click_mode;
    }

    public void setVideo_click_mode(String video_click_mode) {
        this.video_click_mode = video_click_mode;
    }

    public String getFallback_deep_link() {
        return fallback_deep_link;
    }

    public void setFallback_deep_link(String fallback_deep_link) {
        this.fallback_deep_link = fallback_deep_link;
    }

    public int getClick_position() {
        return click_position;
    }

    public void setClick_position(int click_position) {
        this.click_position = click_position;
    }
}
