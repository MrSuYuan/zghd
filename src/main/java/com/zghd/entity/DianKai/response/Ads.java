package com.zghd.entity.DianKai.response;

import java.util.List;

public class Ads {

    private String ad_key;
    private String html_snippet;
    private String mob_adtext;
    private String mob_adlogo;
    private String title;
    private String brand_name;
    private String description;
    private List<String> image_src;
    private String icon_src;
    private int interaction_type;
    private boolean is_download_middle_page;
    private boolean special_download;
    private String click_url;
    private String app_name;
    private String app_package;
    private float app_size;
    private String video_url;
    private boolean prefetch;
    private int video_duration;
    private int video_skip_time;
    private String deeplink_url;
    private boolean video_clickable;
    private String button_text;

    private List<String> report_click;
    private List<String> report_impress;
    private List<String> report_deeplink_click;
    private List<String> report_deeplink_success;
    private List<String> report_deeplink_fail;
    private List<String> report_deeplink_uninstall;
    private List<String> report_deeplink_install;
    private List<String> report_startdown;
    private List<String> report_downsucc;
    private List<String> report_startinstall;
    private List<String> report_installsucc;
    private List<String> report_appactive;
    private List<String> report_app_exists;
    private List<String> report_video_mute;
    private List<String> report_video_unmute;
    private List<String> report_video_close;
    private List<String> report_video_load;
    private List<String> report_video_play;
    private List<String> report_video_pause;
    private List<String> report_video_continue;
    private List<String> report_video_fullscreen;
    private List<String> report_video_unfullscreen;
    private List<String> report_video_complete;
    private List<String> report_video_interrupt;
    private List<String> report_video_pageclose;
    private List<String> report_video_skip;
    private List<String> report_video_error;
    private List<VideoPlay> report_video_play_percentage;

    public String getAd_key() {
        return ad_key;
    }

    public void setAd_key(String ad_key) {
        this.ad_key = ad_key;
    }

    public String getHtml_snippet() {
        return html_snippet;
    }

    public void setHtml_snippet(String html_snippet) {
        this.html_snippet = html_snippet;
    }

    public String getMob_adtext() {
        return mob_adtext;
    }

    public void setMob_adtext(String mob_adtext) {
        this.mob_adtext = mob_adtext;
    }

    public String getMob_adlogo() {
        return mob_adlogo;
    }

    public void setMob_adlogo(String mob_adlogo) {
        this.mob_adlogo = mob_adlogo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImage_src() {
        return image_src;
    }

    public void setImage_src(List<String> image_src) {
        this.image_src = image_src;
    }

    public String getIcon_src() {
        return icon_src;
    }

    public void setIcon_src(String icon_src) {
        this.icon_src = icon_src;
    }

    public int getInteraction_type() {
        return interaction_type;
    }

    public void setInteraction_type(int interaction_type) {
        this.interaction_type = interaction_type;
    }

    public boolean isIs_download_middle_page() {
        return is_download_middle_page;
    }

    public void setIs_download_middle_page(boolean is_download_middle_page) {
        this.is_download_middle_page = is_download_middle_page;
    }

    public boolean isSpecial_download() {
        return special_download;
    }

    public void setSpecial_download(boolean special_download) {
        this.special_download = special_download;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_package() {
        return app_package;
    }

    public void setApp_package(String app_package) {
        this.app_package = app_package;
    }

    public float getApp_size() {
        return app_size;
    }

    public void setApp_size(float app_size) {
        this.app_size = app_size;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public boolean isPrefetch() {
        return prefetch;
    }

    public void setPrefetch(boolean prefetch) {
        this.prefetch = prefetch;
    }

    public int getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(int video_duration) {
        this.video_duration = video_duration;
    }

    public int getVideo_skip_time() {
        return video_skip_time;
    }

    public void setVideo_skip_time(int video_skip_time) {
        this.video_skip_time = video_skip_time;
    }

    public String getDeeplink_url() {
        return deeplink_url;
    }

    public void setDeeplink_url(String deeplink_url) {
        this.deeplink_url = deeplink_url;
    }

    public boolean isVideo_clickable() {
        return video_clickable;
    }

    public void setVideo_clickable(boolean video_clickable) {
        this.video_clickable = video_clickable;
    }

    public String getButton_text() {
        return button_text;
    }

    public void setButton_text(String button_text) {
        this.button_text = button_text;
    }

    public List<String> getReport_click() {
        return report_click;
    }

    public void setReport_click(List<String> report_click) {
        this.report_click = report_click;
    }

    public List<String> getReport_impress() {
        return report_impress;
    }

    public void setReport_impress(List<String> report_impress) {
        this.report_impress = report_impress;
    }

    public List<String> getReport_deeplink_click() {
        return report_deeplink_click;
    }

    public void setReport_deeplink_click(List<String> report_deeplink_click) {
        this.report_deeplink_click = report_deeplink_click;
    }

    public List<String> getReport_deeplink_success() {
        return report_deeplink_success;
    }

    public void setReport_deeplink_success(List<String> report_deeplink_success) {
        this.report_deeplink_success = report_deeplink_success;
    }

    public List<String> getReport_deeplink_fail() {
        return report_deeplink_fail;
    }

    public void setReport_deeplink_fail(List<String> report_deeplink_fail) {
        this.report_deeplink_fail = report_deeplink_fail;
    }

    public List<String> getReport_deeplink_uninstall() {
        return report_deeplink_uninstall;
    }

    public void setReport_deeplink_uninstall(List<String> report_deeplink_uninstall) {
        this.report_deeplink_uninstall = report_deeplink_uninstall;
    }

    public List<String> getReport_deeplink_install() {
        return report_deeplink_install;
    }

    public void setReport_deeplink_install(List<String> report_deeplink_install) {
        this.report_deeplink_install = report_deeplink_install;
    }

    public List<String> getReport_startdown() {
        return report_startdown;
    }

    public void setReport_startdown(List<String> report_startdown) {
        this.report_startdown = report_startdown;
    }

    public List<String> getReport_downsucc() {
        return report_downsucc;
    }

    public void setReport_downsucc(List<String> report_downsucc) {
        this.report_downsucc = report_downsucc;
    }

    public List<String> getReport_startinstall() {
        return report_startinstall;
    }

    public void setReport_startinstall(List<String> report_startinstall) {
        this.report_startinstall = report_startinstall;
    }

    public List<String> getReport_installsucc() {
        return report_installsucc;
    }

    public void setReport_installsucc(List<String> report_installsucc) {
        this.report_installsucc = report_installsucc;
    }

    public List<String> getReport_appactive() {
        return report_appactive;
    }

    public void setReport_appactive(List<String> report_appactive) {
        this.report_appactive = report_appactive;
    }

    public List<String> getReport_app_exists() {
        return report_app_exists;
    }

    public void setReport_app_exists(List<String> report_app_exists) {
        this.report_app_exists = report_app_exists;
    }

    public List<String> getReport_video_mute() {
        return report_video_mute;
    }

    public void setReport_video_mute(List<String> report_video_mute) {
        this.report_video_mute = report_video_mute;
    }

    public List<String> getReport_video_unmute() {
        return report_video_unmute;
    }

    public void setReport_video_unmute(List<String> report_video_unmute) {
        this.report_video_unmute = report_video_unmute;
    }

    public List<String> getReport_video_close() {
        return report_video_close;
    }

    public void setReport_video_close(List<String> report_video_close) {
        this.report_video_close = report_video_close;
    }

    public List<String> getReport_video_load() {
        return report_video_load;
    }

    public void setReport_video_load(List<String> report_video_load) {
        this.report_video_load = report_video_load;
    }

    public List<String> getReport_video_play() {
        return report_video_play;
    }

    public void setReport_video_play(List<String> report_video_play) {
        this.report_video_play = report_video_play;
    }

    public List<String> getReport_video_pause() {
        return report_video_pause;
    }

    public void setReport_video_pause(List<String> report_video_pause) {
        this.report_video_pause = report_video_pause;
    }

    public List<String> getReport_video_continue() {
        return report_video_continue;
    }

    public void setReport_video_continue(List<String> report_video_continue) {
        this.report_video_continue = report_video_continue;
    }

    public List<String> getReport_video_fullscreen() {
        return report_video_fullscreen;
    }

    public void setReport_video_fullscreen(List<String> report_video_fullscreen) {
        this.report_video_fullscreen = report_video_fullscreen;
    }

    public List<String> getReport_video_unfullscreen() {
        return report_video_unfullscreen;
    }

    public void setReport_video_unfullscreen(List<String> report_video_unfullscreen) {
        this.report_video_unfullscreen = report_video_unfullscreen;
    }

    public List<String> getReport_video_complete() {
        return report_video_complete;
    }

    public void setReport_video_complete(List<String> report_video_complete) {
        this.report_video_complete = report_video_complete;
    }

    public List<String> getReport_video_interrupt() {
        return report_video_interrupt;
    }

    public void setReport_video_interrupt(List<String> report_video_interrupt) {
        this.report_video_interrupt = report_video_interrupt;
    }

    public List<String> getReport_video_pageclose() {
        return report_video_pageclose;
    }

    public void setReport_video_pageclose(List<String> report_video_pageclose) {
        this.report_video_pageclose = report_video_pageclose;
    }

    public List<String> getReport_video_skip() {
        return report_video_skip;
    }

    public void setReport_video_skip(List<String> report_video_skip) {
        this.report_video_skip = report_video_skip;
    }

    public List<String> getReport_video_error() {
        return report_video_error;
    }

    public void setReport_video_error(List<String> report_video_error) {
        this.report_video_error = report_video_error;
    }

    public List<VideoPlay> getReport_video_play_percentage() {
        return report_video_play_percentage;
    }

    public void setReport_video_play_percentage(List<VideoPlay> report_video_play_percentage) {
        this.report_video_play_percentage = report_video_play_percentage;
    }
}
