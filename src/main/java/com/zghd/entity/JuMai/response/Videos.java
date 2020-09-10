package com.zghd.entity.JuMai.response;

import java.util.List;

/**
 * Videos 对象
 */
public class Videos {

    //视频地址
    private String video_url;
    //视频广告的时长，单位秒
    private int video_duration;
    //是否预先加载视频
    private boolean prefetch;
    //视频加载成功上报地址
    private List<String> video_loaded_trackers;
    //视频播放开始上报地址
    private List<String> start_play_trackers;
    //视频播放完成上报地址
    private List<String> end_play_trackers;
    //关闭视频上报地址
    private List<String> video_close;
    //跳过视频上报地址
    private List<String> video_skip;
    //HTML 片段,不为空时使用,必须在视频播放完毕后再渲染 HTML片段
    private String html;
    //HTML 展示上报链接
    private List<String> html_exposure_tracking;
    //HTML 关闭上报链接
    private List<String> html_clos_tracking;
    //服务器激励回调上报 URL
    private List<String> callback_trackers;
    //视频播放错误上报地址
    private List<String> error;
    //播放进度上报地址
    private List<Playpercentage> playpercentages;

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(int video_duration) {
        this.video_duration = video_duration;
    }

    public boolean isPrefetch() {
        return prefetch;
    }

    public void setPrefetch(boolean prefetch) {
        this.prefetch = prefetch;
    }

    public List<String> getVideo_loaded_trackers() {
        return video_loaded_trackers;
    }

    public void setVideo_loaded_trackers(List<String> video_loaded_trackers) {
        this.video_loaded_trackers = video_loaded_trackers;
    }

    public List<String> getStart_play_trackers() {
        return start_play_trackers;
    }

    public void setStart_play_trackers(List<String> start_play_trackers) {
        this.start_play_trackers = start_play_trackers;
    }

    public List<String> getEnd_play_trackers() {
        return end_play_trackers;
    }

    public void setEnd_play_trackers(List<String> end_play_trackers) {
        this.end_play_trackers = end_play_trackers;
    }

    public List<String> getVideo_close() {
        return video_close;
    }

    public void setVideo_close(List<String> video_close) {
        this.video_close = video_close;
    }

    public List<String> getVideo_skip() {
        return video_skip;
    }

    public void setVideo_skip(List<String> video_skip) {
        this.video_skip = video_skip;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<String> getHtml_exposure_tracking() {
        return html_exposure_tracking;
    }

    public void setHtml_exposure_tracking(List<String> html_exposure_tracking) {
        this.html_exposure_tracking = html_exposure_tracking;
    }

    public List<String> getHtml_clos_tracking() {
        return html_clos_tracking;
    }

    public void setHtml_clos_tracking(List<String> html_clos_tracking) {
        this.html_clos_tracking = html_clos_tracking;
    }

    public List<String> getCallback_trackers() {
        return callback_trackers;
    }

    public void setCallback_trackers(List<String> callback_trackers) {
        this.callback_trackers = callback_trackers;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public List<Playpercentage> getPlaypercentages() {
        return playpercentages;
    }

    public void setPlaypercentages(List<Playpercentage> playpercentages) {
        this.playpercentages = playpercentages;
    }
}
