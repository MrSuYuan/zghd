package com.zghd.entity.ZGHDRequest;

/**
 * 广告位信息
 */
public class Slot {

    /**
     * 广告位ID
     */
    private String slotId;
    /**
     * 广告位宽度
     */
    private int slotwidth;
    /**
     * 广告位高度
     */
    private int slotheight;
    /**
     * 广告类型
     * 1-	横幅
     * 2-	开屏
     * 3-	插屏
     * 4-	信息流
     * 5-	激励视频
     * 6-	文字链
     */
    private int adtype=5;

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public int getSlotwidth() {
        return slotwidth;
    }

    public void setSlotwidth(int slotwidth) {
        this.slotwidth = slotwidth;
    }

    public int getSlotheight() {
        return slotheight;
    }

    public void setSlotheight(int slotheight) {
        this.slotheight = slotheight;
    }

    public int getAdtype() {
        return adtype;
    }

    public void setAdtype(int adtype) {
        this.adtype = adtype;
    }
}
