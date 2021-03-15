package com.zghd.entity.JingDong.request;

public class Imp {

    //展示id,唯一标识一个展示
    private String id;
    //广告位id
    private String tagid;
    //竞价底价
    private double bidfloor;
    //banner.Html+js类型
    //private ReqBanner banner;
    //原生-需要转为native
    private Native aNative;
    //视频
    private Video video;
    //宏替换
    //private boolean clicktracking;
    //吊起
    private boolean isdeeplink;
    //ios使用
    private boolean isul;
    //创意是否支持https 1 其他返回http创意
    private int secure;
    //私有交易信息
    private Pmp pmp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public double getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(double bidfloor) {
        this.bidfloor = bidfloor;
    }

    /*public ReqBanner getReqBanner() {
        return banner;
    }

    public void setReqBanner(ReqBanner banner) {
        this.banner = banner;
    }*/

    public Native getaNative() {
        return aNative;
    }

    public void setaNative(Native aNative) {
        this.aNative = aNative;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    /*public boolean isClicktracking() {
        return clicktracking;
    }

    public void setClicktracking(boolean clicktracking) {
        this.clicktracking = clicktracking;
    }
*/
    public boolean isIsdeeplink() {
        return isdeeplink;
    }

    public void setIsdeeplink(boolean isdeeplink) {
        this.isdeeplink = isdeeplink;
    }

    public boolean isIsul() {
        return isul;
    }

    public void setIsul(boolean isul) {
        this.isul = isul;
    }

    public int getSecure() {
        return secure;
    }

    public void setSecure(int secure) {
        this.secure = secure;
    }

    public Pmp getPmp() {
        return pmp;
    }

    public void setPmp(Pmp pmp) {
        this.pmp = pmp;
    }
}
