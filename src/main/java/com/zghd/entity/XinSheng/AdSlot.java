package com.zghd.entity.XinSheng;

/**
 * 广告位基本信息
 */
public class AdSlot {

    /**
     * 平台分配的广告位ID
     */
    private String slotid;
    /**
     * 视频广告最小时长，单位秒
     */
    private int min;
    /**
     * 视频广告最大时长，单位秒
     */
    private int max;
    /**
     * 允许的素材类型， 多个mime类型用逗号分隔
     * txt：文字链
     * icon：图文
     * c：富媒体
     * img：等价于jpg,gif,png,webp
     */
    private String mimes;

    public String getSlotid() {
        return slotid;
    }

    public void setSlotid(String slotid) {
        this.slotid = slotid;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getMimes() {
        return mimes;
    }

    public void setMimes(String mimes) {
        this.mimes = mimes;
    }
}
