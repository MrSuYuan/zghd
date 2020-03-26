package com.zghd.entity.JiGuang;

import java.util.List;

/**
 * 广告位参数组
 */
public class Adslot {

    /**
     * 广告位请求唯一标识
     * @request true
     */
    private int index;
    /**
     * 广告位ID
     * @request true
     */
    private String id;
    /**
     * 广告位可视区域高度
     * @request true
     */
    private int height;
    /**
     * 广告位可视区域宽度
     * @request true
     */
    private int width;
    /**
     * 支持的交互方式
     * SURFING 普通链接（H5）
     * DOWNLOAD 应用下载
     * DEEPLINK1 应用直达1，唤起失败时打开落地页
     * DEEPLINK2 应用直达2，唤起失败直接下载应用
     * @request true
     */
    private List<String> support_interaction;
    /**
     * 是否预加载（缓存）视频
     * @request true
     */
    private boolean video_preload;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<String> getSupport_interaction() {
        return support_interaction;
    }

    public void setSupport_interaction(List<String> support_interaction) {
        this.support_interaction = support_interaction;
    }

    public boolean isVideo_preload() {
        return video_preload;
    }

    public void setVideo_preload(boolean video_preload) {
        this.video_preload = video_preload;
    }
}
