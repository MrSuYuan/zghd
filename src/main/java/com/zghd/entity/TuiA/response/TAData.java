package com.zghd.entity.TuiA.response;

public class TAData {


    //图片
    private String imageUrl;
    //广告位活动链接
    private String activityUrl;
    //素材id
    private long sckId;
    //曝光
    private String reportExposureUrl;
    //点击
    private String reportClickUrl;
    //标题
    private String extTitle;
    //描述
    private String extDesc;
    //尺寸
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public long getSckId() {
        return sckId;
    }

    public void setSckId(long sckId) {
        this.sckId = sckId;
    }

    public String getReportExposureUrl() {
        return reportExposureUrl;
    }

    public void setReportExposureUrl(String reportExposureUrl) {
        this.reportExposureUrl = reportExposureUrl;
    }

    public String getReportClickUrl() {
        return reportClickUrl;
    }

    public void setReportClickUrl(String reportClickUrl) {
        this.reportClickUrl = reportClickUrl;
    }

    public String getExtTitle() {
        return extTitle;
    }

    public void setExtTitle(String extTitle) {
        this.extTitle = extTitle;
    }

    public String getExtDesc() {
        return extDesc;
    }

    public void setExtDesc(String extDesc) {
        this.extDesc = extDesc;
    }

}
