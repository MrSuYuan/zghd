package com.zghd.entity.ZGHDNewsResponse;

/**
 * 图片
 */
public class Image {

    //图片地址
    private String imageUrl;
    //图片宽度
    private int imageWidth;
    //图片高度
    private int imageHeight;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }
}
