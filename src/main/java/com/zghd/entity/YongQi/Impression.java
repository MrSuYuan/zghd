package com.zghd.entity.YongQi;

/**
 * 曝光对象
 */
public class Impression {

    /**
     * 曝光id
     */
    private String id;
    /**
     * 底价,单位是分/千次曝光,即 CPM
     */
    private double bidfloor;
    /**
     * video对象
     */
    private Video video;
    /**
     * 广告位id
     */
    private String tagid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(double bidfloor) {
        this.bidfloor = bidfloor;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }
}
