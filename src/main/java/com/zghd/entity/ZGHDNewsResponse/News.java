package com.zghd.entity.ZGHDNewsResponse;

import java.util.List;

/**
 * 新闻内容
 */
public class News {
    //新闻id
    private String id;
    //标题
    private String title;
    //新闻来源/视频作者
    private String source;
    //新闻更新时间
    private String updateTime;
    //根据 update_trime 转换成时间戳
    private long timestamp;
    //新闻是否是视频类型 1是 2不是
    private int isVideo;
    //新闻图片,视频类型一般只有1张缩略图
    private List<Image> images;
    //新闻类目
    private String cat;
    //新闻曝光监测
    private List<String> showUrl;
    //新闻落地页
    private String url;
    //点击上报
    private List<String> clickUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(int isVideo) {
        this.isVideo = isVideo;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public List<String> getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(List<String> showUrl) {
        this.showUrl = showUrl;
    }

    public List<String> getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(List<String> clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
