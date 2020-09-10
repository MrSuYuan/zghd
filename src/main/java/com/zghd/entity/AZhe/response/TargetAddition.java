package com.zghd.entity.AZhe.response;

import java.util.List;

/**
 * 监测链接集合
 */
public class TargetAddition {

    //下一次曝光请求的停留时间
    private int duration;
    //曝光检测的类型:VIEW(曝光)、CLICK（点击）、VIDEO_FINISH（播放完成）、CLOSE（关闭）、VIDEO_TIMER（播放定时打点）
    private String type;
    //检测链接集合，需要客户端请求发出
    private List<String> urls;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
