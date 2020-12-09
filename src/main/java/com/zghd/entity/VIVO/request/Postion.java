package com.zghd.entity.VIVO.request;

/**
 * 广告位信息
 */
public class Postion {
    /**
     * 广告位id
     */
    private String positionId;
    /**
     * 广告位类型
     * 2开屏 3banner 4插屏 5原生 9激励视频
     */
    private int displayType;
    /**
     * 广告位宽
     */
    private int width;
    /**
     * 广告位高
     */
    private int height;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
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
