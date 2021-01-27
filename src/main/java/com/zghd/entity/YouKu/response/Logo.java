package com.zghd.entity.YouKu.response;

/**
 * logo
 */
public class Logo {

    //素材URL
    private String url;
    //素材文件类型（jpg/png等）
    private String type;
    //素材尺寸宽度
    private int width;
    //素材尺寸高度
    private int height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
