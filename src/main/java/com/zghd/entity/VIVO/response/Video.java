package com.zghd.entity.VIVO.response;

/**
 * 视频信息
 */
public class Video {
    /**
     * 视频url
     */
    private String videoUrl;
    /**
     * 时长
     */
    private int duration;
    /**
     * 大小kb
     */
    private int size;
    /**
     * 宽
     */
    private int width;
    /**
     * 高
     */
    private int height;
    /**
     * 预览图
     */
    private String previewImgUrl;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public void setPreviewImgUrl(String previewImgUrl) {
        this.previewImgUrl = previewImgUrl;
    }
}
