package com.zghd.entity.YouKu.response;

/**
 * 视频信息
 */
public class Video {

    //素材URL
    private String url;
    //视频素材时长
    private int vl;
    //视频素材大小，以B为单位
    private int size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVl() {
        return vl;
    }

    public void setVl(int vl) {
        this.vl = vl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
